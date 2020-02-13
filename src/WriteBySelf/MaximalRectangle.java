package WriteBySelf;

import java.util.Stack;
/**
 * ����һ�����;���map�����е�ֵֻ��0��1��������ȫ��1�����о����������ľ�������Ϊ1������
 * @author jasonborn
 *
 */
public class MaximalRectangle {
	public static int maxRecSize(int[][] map) {
		if(map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			maxArea  = Math.max(maxArea, maxRecFromBottom(height));
		}
		return maxArea;
	}

	private static int maxRecFromBottom(int[] height) {
		// TODO �Զ����ɵķ������
		if(height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> indexStack = new Stack<>();
		for(int i = 0; i < height.length; i++) {
			while(!indexStack.isEmpty() && height[indexStack.peek()] >= height[i]) {
				int j = indexStack.pop();
				int k = indexStack.isEmpty() ? -1 : indexStack.peek();
				maxArea = Math.max((i - k - 1) * height[j], maxArea);
			}
			indexStack.push(i);
		}
		while(!indexStack.isEmpty()) {
			int j = indexStack.pop();
			int k = indexStack.isEmpty() ? -1 : indexStack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(curArea, maxArea);
		}
		return maxArea;
	}
	  public static void main(String[] args) {
	        int[][] map = {{0, 0, 0, 0, 1}, 
	        			   {0, 0, 0, 1, 1}, 
	        			   {1, 0, 0, 1, 1},
	        			   {1, 1, 1, 1, 1},
	        			   {1, 1, 1, 1, 1},
	        			   {1, 1, 1, 1, 1}};
	        int maxArea = maxRecSize(map);
	        System.out.println("maxArea = " + maxArea);
	    }
}
