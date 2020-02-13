package nowcoder.advanced.day01;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/3 17:44
 */
public class MountainsAndFlame {
    public static void main(String[] args) {
        // ����������ֵ�����鳤�Ⱥ�������������
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
            	arr[i] = in.nextInt();         	
            }
            System.out.println(communications(arr));
        }
        in.close();
    }

    // �ڻ��������� i λ�õ���һλ��û������ +1�����׾�Ϊ 0
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    // arr Ϊ��������
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        // �ҵ����ֵλ�ã���һ����
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        // value Ϊ���ֵ
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        // ���Ƚ����ֵ����ջ��
        Stack<Pair> stack = new Stack<Pair>();
        stack.push(new Pair(value));
        // ��Ϊ�Ǵ� maxIndex ����һ��λ�ÿ�ʼ�����ģ��������ֵ�ٴ����˵����������
        while (index != maxIndex) {
            // �����еĵ�ǰֵ
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                // res += getInernalSum(times) +times;
                // res += stack.isEmpty() ? 0 : times;
                // ������Ϊ��������ϲ�
                res += getInternalSum(times) + 2 * times;
            }
            // �����ǰֵʹ��ջ��������Ȼ��ջ���͵���֮��¶����ջ��ֵ��ͬ����ԭ��ջ����Ŀ +1
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
                // ����͵���֮��¶����ջ��ֵ����ȣ��򽫵�ǰֵ����ջ��
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);
        }
        // ��������֮��ʣ��ջ��Ԫ�ؽ��н���
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }
}