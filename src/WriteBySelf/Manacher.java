package WriteBySelf;
/**
 * 求一个字符串的最大回文的长度
 * @author jasonborn
 *
 */
public class Manacher {
	public static char[] manacherString(String str) {
		char[] chs = str.toCharArray();
		char[] chsAdd = new char[str.length() * 2 + 1];
		int index = 0;
		for(int i = 0; i < chsAdd.length; i++) {
			chsAdd[i] = (i & 1) == 0 ? '#' : chs[index++];
		}
		return chsAdd;
	}
	
	public static int maxLcpsLength(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		char[] chs = manacherString(str);
		int[] pArr = new int[chs.length];
		int C = -1;
		int R = -1;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < chs.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while(i + pArr[i] < chs.length && i - pArr[i] >= 0) {
				if(chs[i + pArr[i]] == chs[i - pArr[i]]) {
					pArr[i]++;
				}else {
					break;
				}
			}
			if(i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(pArr[i], max);
		}
		return max - 1;
	}
	public static void main(String[] args) {
        int length = maxLcpsLength("123abccbadbccba4w2");
        System.out.println(length);
    }
}
