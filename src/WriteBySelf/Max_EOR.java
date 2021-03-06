package WriteBySelf;
/**
 *给定一个数组，求子数组的最大异或和。
 *一个数组的异或和为，数组中所有的数异或起来的结果。
 * @author jasonborn
 *
 */
public class Max_EOR {
	public static int comparator(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int eor = 0;
		int[] dp = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			max = Math.max(eor, max);
			for(int start = 1; start < i; start++) {
				int curEor = eor ^ dp[start - 1];
				max = Math.max(curEor, max);
			}
		}
		return max;
	}
	public static class Node {
		public Node[] nexts = new Node[2];
	}
	public static class NumTrie {
		public Node head = new Node();
		
		public void add(int num) {
			Node cur = this.head;
			for(int move = 31; move >=0; move--) {
				int path = (num >> move) & 1;
				cur.nexts[path] = cur.nexts[path] != null ? cur.nexts[path] : new Node();
				cur = cur.nexts[path];
			}
		}
		public int maxXor(int num) {
			Node cur = this.head;
			int res = 0;
			for(int move = 31; move >=0; move--) {
				int path = (num >> move) & 1;
				int best = move == 31 ? path : (path ^ 1);
				best = cur.nexts[best] != null ? best : (best ^ 1);
				res |= (path ^ best) << move;
				cur = cur.nexts[best];
			}
			return res;
		}
		
	}
	public static int  maxXorSubarray(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int eor = 0;
		NumTrie numTrie = new NumTrie();
		numTrie.add(0);
		for(int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			max = Math.max(max, numTrie.maxXor(eor));
		}
		return max;
	}
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 30;
		int maxValue = 50;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = maxXorSubarray(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
