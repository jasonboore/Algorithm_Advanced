package WriteBySelf;
/**
 * 解决一个字符串是否是另一个字符串子串的问题
 * @author jasonborn
 *
 */
public class KMP {
	public static int getIndexOf(String str1, String str2) {
		if(str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
			return -1;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int index1 = 0;
		int index2 = 0;
		int[] nexts = getNextArray(str2);
		while(index1 < str1.length() && index2 < str2.length()) {
			if(chs1[index1] == chs2[index2]) {
				index1++;
				index2++;
			}else if(nexts[index2] == -1) {
				index1++;
			}else {
				index2 = nexts[index2];
			}
		}
		return index2 == str2.length() ? index1 - index2 : -1;
	}

	public static int[] getNextArray(String str2) {
		// TODO 自动生成的方法存根
		if(str2.length() == 1) {
			return new int[] {-1};
		}
		char[] chs = str2.toCharArray();
		int[] nexts = new int[chs.length];
		nexts[0] = -1;
		nexts[1] = 0;
		int cn = 0;
		int pos = 2;
		while(pos < chs.length) {
			if(chs[pos - 1] == chs[cn]) {
				nexts[pos++] = ++cn;
			}else if(cn > 0) {
				cn = nexts[cn];
			}else {
				nexts[pos++] = 0;
			}
		}
		return nexts;
	}
	public static void main(String[] args) {
		 String str = "abcabcababaccc";
	     String match = "ababa";
	     System.out.println(getIndexOf(str, match));
	}
}
