package WriteBySelf;
/**
 * BFPRT
 * 解决在一个数组中找到第K小的数
 * @author jasonborn
 *
 */
public class BFPRT {
	public static int[] getMinKNumsByHeap(int[] arr, int K) {
		if(arr == null || K > arr.length) {
			return arr;
		}
		int[] heap = new int[K];
		for(int i = 0; i < K; i++) {
			heapInsert(heap,arr[i], i);
		}
		for(int i = K; i < arr.length; i++) {
			if(arr[i] < heap[0]) {
				heap[0] = arr[i];
				heapify(heap, 0, K);
			}
		}
		return heap;
	}

	private static void heapify(int[] arr, int i, int size) {
		// TODO 自动生成的方法存根
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		while(left < size) {
			int largest = right < size && arr[right] > arr[left] ? right : left;
			largest = arr[largest] > arr[i] ? largest : i;
			if(largest != i) {
				swap(arr, i, largest);
			}else {
				break;
			}
			i = largest;
			left = 2 * i + 1;
			right = left + 1;
		}
	}

	private static void heapInsert(int[] arr,int value, int index) {
		// TODO 自动生成的方法存根
		arr[index] = value;
		while(arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index , (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}
	
	//------------------分割-------------------------
	
	public static int[] getMinKNumsByBFPRT(int[] arr, int K) {
		if(K < 1 || K > arr.length) {
			return arr;
		}
		int minKthNum = getMinKthByBFPRT(arr, K);
		int[] res = new int[K];
		int index = 0;
		for(int i = 0; i < arr.length;i++) {
			if(arr[i] < minKthNum) {
				res[index++] = arr[i];
			}
		}
		for(; index < K; index++) {
			res[index] = minKthNum;
		}
		return res;
	}
	
	private static int getMinKthByBFPRT(int[] arr, int k) {
		// TODO 自动生成的方法存根
		 int[] copyArr = copyArray(arr);
		 return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
	}
	 private static int bfprt(int[] arr, int begin, int end, int k) {
		// TODO 自动生成的方法存根
		 if(begin == end) {
			 return arr[begin];
		 }
		 int pivot = medianOfMedians(arr, begin, end);
		 int[] pivotRange = partition(arr, begin, end, pivot);
		 if(k >= pivotRange[0] && k <= pivotRange[1]) {
			 return arr[k];
		 }else if(k < pivotRange[0]) {
			 return bfprt(arr, begin, pivotRange[0] - 1, k);
		 }else {
			 return bfprt(arr,  pivotRange[1] + 1, end, k);
		 }
	}

	private static int[] partition(int[] arr, int begin, int end, int pivot) {
		// TODO 自动生成的方法存根
		int less = begin - 1;
		int more = end + 1;
		int cur = begin;
		while(cur < more) {
			if(arr[cur] < pivot) {
				swap(arr, ++less, cur++);
			}else if(arr[cur] > pivot) {
				swap(arr, cur, --more);
			}else {
				cur++;
			}
		}
		return new int[]{less + 1, more - 1};
	}

	private static int medianOfMedians(int[] arr, int begin, int end) {
		// TODO 自动生成的方法存根
		int num = end - begin + 1;
		int offSet = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offSet];
		for(int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = Math.min(beginI + 4, end);
			mArr[i] = getMedian(arr, beginI, endI);
		}
		return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	private static int getMedian(int[] arr, int beginI, int endI) {
		// TODO 自动生成的方法存根
		insertSort(arr, beginI, endI);
		int sum = beginI + endI;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	private static void insertSort(int[] arr, int beginI, int endI) {
		// TODO 自动生成的方法存根
		for(int i = beginI + 1; i <= endI; i++) {
			for(int j = i; j > beginI; j--) {
				if(arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				}else {
					break;
				}
			}
		}
	}

	public static int[] copyArray(int[] arr) {
	        int[] res = new int[arr.length];
	        for (int i = 0; i != res.length; i++) {
	            res[i] = arr[i];
	        }
	        return res;
	    }

	private static void swap(int[] arr, int i, int j) {
		// TODO 自动生成的方法存根
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	 public static void printArray(int[] array){
	        for (int i = 0; i < array.length; i++) {
	            System.out.print(array[i] + " ");
	        }
	        System.out.println();
	    }
	 public static void main(String[] args) {
	        int[] demo ={6,9,1,3,1,2,2,5,6,1,3,5,9,7,2,5,6,1,9};
	        printArray(getMinKNumsByHeap(demo, 10));
	        printArray(getMinKNumsByBFPRT(demo, 10));
	    }
}
