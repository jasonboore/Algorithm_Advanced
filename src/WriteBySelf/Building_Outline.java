package WriteBySelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;


/***
 * ����һ��N��3�еĶ�ά���飬ÿһ�б�ʾһ����¥��һ����N����¥��
 * ���д�¥�ĵײ���������X���ϣ�ÿһ�е�3��ֵ(a, b, c)����ÿ����¥��(a, 0)��ʼ��
 * ��(b, 0)�������߶�Ϊc.��������ݿ��Ա�֤a<b,��a,b,c��Ϊ������
 * ��¥֮������غϣ����������������ߡ�
 * @author jasonborn
 *
 */
public class Building_Outline {
	public static class Node {
		public int pos;
		public int height;
		public boolean isUp;
		
		public Node(int pos, int height, boolean isUp) {
			this.pos = pos;
			this.height = height;
			this.isUp = isUp;
		}
	}
	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			// TODO �Զ����ɵķ������
			if(o1.pos != o2.pos) {
				return o1.pos - o2.pos;
			}
			if(o1.isUp != o2.isUp) {
				return o1.isUp ? -1 : 1;
			}
			return 0;
		}
		
	}
	
	public static java.util.List<java.util.List<Integer>> buildingOutline(int[][] buildings) {
		Node[] nodes = new Node[buildings.length * 2];
		int index = 0;
		for(int i = 0; i < buildings.length; i++) {
			nodes[index++] = new Node(buildings[i][0], buildings[i][2], true);
			nodes[index++] = new Node(buildings[i][1], buildings[i][2], false);
		}
		Arrays.sort(nodes, new NodeComparator());
		TreeMap<Integer, Integer> htMap = new TreeMap<>();
		TreeMap<Integer, Integer> phMap = new TreeMap<>();
		for(int i = 0; i < nodes.length; i++) {
			if(nodes[i].isUp) {
				if(!htMap.containsKey(nodes[i].height)) {
					htMap.put(nodes[i].height, 1);
				}else {
					htMap.put(nodes[i].height, htMap.get(nodes[i].height) + 1);
				}
			}else {
				if(htMap.containsKey(nodes[i].height)) {
					if(htMap.get(nodes[i].height) == 1) {
						htMap.remove(nodes[i].height);
					}else {
						htMap.put(nodes[i].height, htMap.get(nodes[i].height) - 1);
					}
				}
			}
			if(htMap.isEmpty()) {
				phMap.put(nodes[i].pos, 0);
			}else {
				phMap.put(nodes[i].pos, htMap.lastKey());
			}
		}
		int start = 0;
		int height = 0;
		java.util.List<java.util.List<Integer>> list = new ArrayList<>();
		for(java.util.Map.Entry<Integer, Integer> entry : phMap.entrySet()) {
			int curPosition = entry.getKey();
			int curHeight = entry.getValue();
			if(height != curHeight) {
				if(height != 0) {
					java.util.List<Integer> temp = new ArrayList<>();
					temp.add(start);
					temp.add(curPosition);
					temp.add(height);
					list.add(temp);
				}
				start = curPosition;
				height = curHeight;
			}
		}
		return list;
	}
	public static void main(String[] args) {
		int[][] buildings = {{1,2,2},
							 {1,4,3},
							 {3,6,5},
							 {5,8,2},
							 {8,9,1},
							 {10,13,3},
							 {11,12,1}};
		java.util.List<java.util.List<Integer>> list = buildingOutline(buildings);
		Iterator<java.util.List<Integer>> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
