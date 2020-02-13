package nowcoder.advanced.day01;

import java.util.LinkedList;


public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // LinkedList ����һ����׼��˫������
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        // ���ɵĽ������
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //����˫�˶��У����˫�˶��в�Ϊ�գ�����β���(������±�)��Ӧ�����е�ֵ�Ƿ�С�ڵ��ڵ�ǰֵ
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            // ����һֱ������ֱ��������Ȼ����ϵ�ǰֵ��
            qmax.addLast(i);
            // ����ӷ���ͨ�õģ����Ǽ�������Ը��ⶨ�Ƶ�
            // �����ڵ�ʱ�򣨵������γ�֮�������������ڣ��������γɹ����в������
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //�ж��±����
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        printArray(getMaxWindow(arr, 3));
    }
}