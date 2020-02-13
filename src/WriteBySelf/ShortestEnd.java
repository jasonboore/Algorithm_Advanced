package WriteBySelf;
/**
 * 添加最少的字符使其成为一个回文字符串
 * 
 * @author jasonborn
 *
 *
 */
public class ShortestEnd {
	public static char[] manacherString(String str) {
		char[] chs = str.toCharArray();
		char[] chsAdd = new char[2 * chs.length + 1];
		int index = 0;
		for(int i = 0; i < chsAdd.length; i++) {
			chsAdd[i] = (i & 1) == 0 ? '#' : chs[index++];
		}
		return chsAdd;
	}
	public static String shortestEnd(String str) {
		if(str == null || str.length() == 0) {
			return null;
		}
		char[] chs = manacherString(str);
		int[] pArr = new int[chs.length];
		int C = -1;
		int R = -1;
		int maxContain = Integer.MIN_VALUE;
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
			if(R == chs.length) {
				maxContain = pArr[i];
				break;
			}
		}
		char[] end = new char[str.length() - maxContain + 1];
		for(int i = 0; i < end.length; i++) {
			end[end.length - 1 - i] = chs[2 * i + 1];
		}
		return String.valueOf(end);
	}
	public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));
    }
}
