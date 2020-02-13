package WriteBySelf;

public class LongestSumSubArrayLengthInPositiveArray {
  public static int getMaxLength(int[] arr, int aim) {
	  if(arr.length == 0 || arr == null || aim <= 0) {
		  return 0;
	  }
	  int L = 0;
	  int R = 0;
	  int res = 0;
	  int sum = arr[0];
	  while(R < arr.length) {
		  if(sum == aim) {
			  res = Math.max(res, R - L + 1);
			  sum -= arr[L++];
		  }else if(sum < aim) {
			  R++;
			  if(R == arr.length) {
				  break;
			  }
			  sum += arr[R];
		  }else {
			  sum -= arr[L++];
		  }
	  }
	  return res;
  }
  public static int[] generatePositiveArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 10) + 1;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 20;
		int k = 15;
		int[] arr = generatePositiveArray(len);
		printArray(arr);
		System.out.println(getMaxLength(arr, k));

	}
}
