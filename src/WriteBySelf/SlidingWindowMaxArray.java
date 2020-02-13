package WriteBySelf;

import java.util.LinkedList;
/**
 * 生成窗口最大值
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置
 * 请实现一个函数，输入一个数组arr和窗口大小为w
 * 输出一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
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
