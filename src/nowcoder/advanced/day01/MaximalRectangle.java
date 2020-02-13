package nowcoder.advanced.day01;

import java.util.Stack;

/**
 * @Author GJXAIOU
 * @Date 2020/1/3 15:59
 */
public class MaximalRectangle {
    // ԭ���⣨���� 0 1 ����
    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // �γɸ������飺���� [10 11][2 1 2 2]�ȵ�
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    // �������������һ���������ֱ��ͼ�Ļ��������ҵ�������
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        // ׼��һ������ջ��ջ�з����±�
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            // ��ջ��Ϊ�գ��ҵ�ǰ��С�ڵ���ջ��
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                // ����ջ��
                int j = stack.pop();
                // k Ϊ��߽� ��������������������ʲô��
                int k = stack.isEmpty() ? -1 : stack.peek();
                // i Ϊ��ǰ���������ұ߽磬�Լ��� k λ����
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        // �������֮��ջ��ʣ��Ԫ�ؽ��н���
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        int maxArea = maxRecSize(map);
        System.out.println("maxArea = " + maxArea);
    }
}