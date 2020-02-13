package WriteBySelf;

import java.util.LinkedList;
/**
 * ���ɴ������ֵ
 * ��һ����������arr��һ����СΪw�Ĵ��ڴ����������߻������ұߣ�����ÿ�����ұ߻�һ��λ��
 * ��ʵ��һ������������һ������arr�ʹ��ڴ�СΪw
 * ���һ������Ϊn-w+1������res��res[i]��ʾÿһ�ִ���״̬�µ����ֵ
 * @author jasonborn
 *
 */
public class SlidingWindowMaxArray {
	public static int[] getMaxWindow(int[] arr, int w) {
		if(arr == null || arr.length < w || w < 1) {
			return null;
		}
		LinkedList<Integer> qMax = new LinkedList<>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
				qMax.pollLast();
			}
			qMax.addLast(i);
			if(qMax.peekFirst() == i - w) {
				qMax.pollFirst();
			}
			if(i >= w - 1) {
				res[index++] = arr[qMax.peekFirst()];
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
