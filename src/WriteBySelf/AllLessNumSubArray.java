package WriteBySelf;

import java.util.LinkedList;

/**
 * ���ֵ��ȥ��СֵС�ڻ����num������������
 * ����һ������arr��һ������num������������ֵ��ȥ��СֵС�ڵ���num
 * Ҫ��ʱ�临�Ӷ�o(N)
 * @author jasonborn
 *
 */
public class AllLessNumSubArray {
	public static int getNum1(int[] arr, int num) {
		int res = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = i; j < arr.length; j++) {
				if(isValid(arr, i, j, num)) {
					res++;
				}
			}
		}
		return res;
	}
	public static boolean isValid(int[] arr, int start, int end, int num) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = start; i <= end; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
		return max - min <= num;
	}
	
	public static int getNum(int[] arr, int num) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		LinkedList<Integer> qmax = new LinkedList<>();
		LinkedList<Integer> qmin = new LinkedList<>();
		int L = 0;
		int R = 0;
		int res = 0;
		while(L < arr.length) {
			while(R < arr.length) {
				while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
					qmax.pollLast();
				}
				qmax.addLast(R);
				while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
					qmin.pollLast();
				}
				qmin.addLast(R);
				
				if(arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
					break;
				}
				R++;
			}
			if(L == qmax.peekFirst()) {
				qmax.pollFirst();
			}
			if(L == qmin.peekFirst()) {
				qmin.pollFirst();
			}
			res += R - L;
			L++;
		}
		return res;
	}
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			
			if (getNum1(arr1, 10) != getNum(arr2, 10)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
		int[] arr = generateRandomArray(maxSize, maxValue);
		System.out.println(getNum1(arr, 2));
		System.out.println(getNum(arr, 2));
		
	}
}
