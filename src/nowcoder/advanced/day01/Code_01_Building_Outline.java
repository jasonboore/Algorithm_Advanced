package nowcoder.advanced.day01;

import java.util.*;
import java.util.Map.Entry;

public class Code_01_Building_Outline {
    // ��λ�ã��߶ȣ��ϻ����£��ṹ
    public static class Node {
        public boolean isUp;
        public int posi;
        public int h;

        public Node(boolean bORe, int position, int height) {
            isUp = bORe;
            posi = position;
            h = height;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) {
                return o1.posi - o2.posi;
            }
            // ����ʱ�����ͬһ��λ�ó����������ϣ����ϵ���ǰ�棬�µ��ں���
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }

    public static List<List<Integer>> buildingOutline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            // ��ֹ������Ϣ����
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        // NOde ֮��ֻ����λ������
        Arrays.sort(nodes, new NodeComparator());
        // key Ϊ�߶ȣ� value Ϊ�ø߶ȳ��ֵĴ���
        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            // ����� UP
            if (nodes[i].isUp) {
                // ���֮ǰû�г��ֹ��ø߶ȣ�����룬���Ҽ�¼���ִ���Ϊ 1
                if (!htMap.containsKey(nodes[i].h)) {
                    htMap.put(nodes[i].h, 1);
                } else {
                    // ���ǵ�һ�γ��֣������ø߶ȣ�����Ϊ֮ǰ���ִ��� + 1��
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
            } else {
                if (htMap.containsKey(nodes[i].h)) {
                    // ֻ��һ�Σ���ƵΪ 1�������ֱ��ɾ����
                    if (htMap.get(nodes[i].h) == 1) {
                        htMap.remove(nodes[i].h);
                    } else {
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                    }
                }
            }
            if (htMap.isEmpty()) {
                // pmMap ��¼ÿһ������λ�õ����߶ȣ����Ǽ�����֮�� TreeMap �е����߶�ֵ�������ڿ����߶��Ƿ�仯�������߽�
                pmMap.put(nodes[i].posi, 0);
            } else {
                pmMap.put(nodes[i].posi, htMap.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        // Ĭ�ϸÿ�ʼ�߶�Ϊ 0
        int start = 0;
        int height = 0;
        // ��Ϊ�� TreeMap�������ó���ֵ����������ģ�����λ��С���ȱ���
        for (Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            // ֮ǰ�ĸ߶Ȳ��������ڸ߶�
            if (height != curMaxHeight) {
                // ���֮ǰ�߶Ȳ�Ϊ 0 �����ұ仯�ˣ�˵��һ��������Ҫ��β��
                if (height != 0) {
                    List<Integer> newRecord = new ArrayList<Integer>();
                    newRecord.add(start);
                    newRecord.add(curPosition);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                // �����Ϊ 0 �����൱��һ�������߸ո�������û����ֹλ�ã�����û����¼
                start = curPosition;
                height = curMaxHeight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, 3}, {2, 4, 4}, {5, 6, 1}};
        List<List<Integer>> lists = buildingOutline(arr);
        Iterator<List<Integer>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
