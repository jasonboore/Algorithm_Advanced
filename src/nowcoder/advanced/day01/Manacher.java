package nowcoder.advanced.day01;


public class Manacher {
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        // ���İ뾶����
        int[] pArr = new int[charArr.length];
        // index  Ϊ�Գ����� C
        int index = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            // 2 * index - 1 ���Ƕ�Ӧ i' λ�ã�R> i,��ʾ i �ڻ����ұ߽����棬����������һ�������������
            pArr[i] = R > i ? Math.min(pArr[2 * index - i], R - i) : 1;
            // �������������һ�£����� 1 �� 4 ��ɹ������� 2 ��3 ��ʧ��������ұ߽粻�ı䣻�����Լ�д�� if-else ���⡣
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        int length = maxLcpsLength("123abccbadbccba4w2");
        System.out.println(length);
    }
}