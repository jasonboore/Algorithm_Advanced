package WriteBySelf;
/**
 * ��������arr��arr�����е�ֵ��Ϊ�����Ҳ��ظ���
 * ÿ��ֵ���� һ����ֵ�Ļ��ң�ÿ����ֵ�Ļ��ҿ���ʹ�������ţ�
 * �ٸ���һ ������aim����Ҫ�ҵ�Ǯ������Ǯ�ж����ַ����� 
 * @author jasonborn
 *
 */

import java.util.HashMap;

public class CoinsWay {
	public static int coins1(int[] arr, int aim) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		return process1(arr, 0, aim);
	}

	private static int process1(int[] arr, int index, int aim) {
		int res = 0;
		if(index == arr.length) {
			return aim == 0 ? 1 : 0;
		}else {
			for(int i = 0; aim - arr[index] * i >= 0; i++) {
				res += process1(arr, index + 1, aim - arr[index] * i);
			}
		}
		return res;
	}
	
	public static int coins2(int[] arr, int aim) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		return process2(arr, 0, aim);
	}
	public static HashMap<String, Integer> map = new HashMap<>();
	private static int process2(int[] arr, int index, int aim) {
		int res = 0;
		if(index == arr.length) {
			return aim == 0 ? 1 : 0;
		}else {
			for(int i = 0; aim - arr[index] * i >= 0; i++) {
				String key = String.valueOf(index + 1) + "_" +  String.valueOf(aim - arr[index] * i);
				if(map.containsKey(key)) {
					res += map.get(key);
				}else {
					res += process1(arr, index + 1, aim - arr[index] * i);
				} 
			}
			map.put(String.valueOf(index) + "_" + String.valueOf(aim), res);
		}
		return res;
	}
	public static int coinsDp(int[] arr, int aim) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		return processDp(arr, aim);
	}
	public static int processDp(int[] arr, int aim) {
		int[][] dp = new int[arr.length][aim + 1];
		for(int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= aim; j++) {
			dp[0][arr[0] * j] = 1;
		}
		for(int i = 1; i < arr.length; i++) {
			for(int j = 1; j <= aim; j++) {
				dp[i][j] = j - arr[i] >=0 ? dp[i - 1][j] + dp[i][j - arr[i]] : dp[i - 1][j];
			}
		}
		return dp[arr.length - 1][aim];
	}
	public static void main(String[] args) {
		int[] coins = { 10, 5, 1, 25 };
		int aim = 2000;

		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		System.out.println(coins1(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");

		start = System.currentTimeMillis();
		System.out.println(coins2(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");

		aim = 20000;

		start = System.currentTimeMillis();
		System.out.println(coinsDp(coins, aim));
		end = System.currentTimeMillis();
		System.out.println("cost time : " + (end - start) + "(ms)");

	}
}
