package WriteBySelf;

import java.util.Scanner;
import java.util.Stack;
/**
 * 给定一个数组，代表一个环形的山，每座山上放置烽火。
 * 1.相邻的山可以互相看见烽火
 * 2.如果两座山峰不相邻，A山峰到B山峰路径上所有值都小于等于A B的最小值，则可以看见。
 * 求能相互看见的山峰的对数
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
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		return times == 1 ? 0 : (long)times * (long)(times - 1) / 2L;
	}
	private static int nextIndex(int size, int Index) {
		// TODO 自动生成的方法存根
		return Index + 1 == size ? 0 : Index + 1;
	}
}
