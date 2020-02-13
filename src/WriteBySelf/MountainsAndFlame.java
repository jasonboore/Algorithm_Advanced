package WriteBySelf;

import java.util.Scanner;
import java.util.Stack;
/**
 * ����һ�����飬����һ�����ε�ɽ��ÿ��ɽ�Ϸ��÷��
 * 1.���ڵ�ɽ���Ի��࿴�����
 * 2.�������ɽ�岻���ڣ�Aɽ�嵽Bɽ��·��������ֵ��С�ڵ���A B����Сֵ������Կ�����
 * �����໥������ɽ��Ķ���
 * @author jasonborn
 *
 */
public class MountainsAndFlame {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()) {
			int size = in.nextInt();
			int[] arr = new int[size];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = in.nextInt();
			}
			System.out.println(communications(arr));
		}
		in.close();
	}
	public static class Pair {
		public int times;
		public int value;
		public Pair(int value) {
			this.value = value;
			this.times = 1;
		}
	}
	private static long communications(int[] arr) {
		// TODO �Զ����ɵķ������
		if(arr == null || arr.length < 2) {
			return 0;
		}
		long num = 0;
		int maxIndex = 0;
		int size = arr.length;
		for(int i = 0;i < size; i++) {
			maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
		}
		int value = arr[maxIndex];
		int index = nextIndex(size, maxIndex);
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(value));
		while(index != maxIndex) {
			 value = arr[index];
			 while(!stack.isEmpty() && value > stack.peek().value) {
				 int times = stack.pop().times;
				 num += getInternalSum(times) + 2 * times;
			 }
			 if(value == stack.peek().value) {
				 stack.peek().times++;
			 }else {
				 stack.push(new Pair(value));
			 }
			 index = nextIndex(size, index);
		}
		while(!stack.isEmpty()) {
			int times = stack.pop().times;
			num += getInternalSum(times);
			if(!stack.isEmpty()) {
				num += times;
				if(stack.size() > 1) {
					num += times;
				}else {
					num += stack.peek().times > 1 ? times : 0;
				}
			}
		}
		return num;
	}
	private static long getInternalSum(int times) {
		// TODO �Զ����ɵķ������
		return times == 1 ? 0 : (long)times * (long)(times - 1) / 2L;
	}
	private static int nextIndex(int size, int Index) {
		// TODO �Զ����ɵķ������
		return Index + 1 == size ? 0 : Index + 1;
	}
}
