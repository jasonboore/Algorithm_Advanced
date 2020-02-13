package nowcoder.advanced.day01;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ����
 */
public class Code_02_SkipList {

    public static class SkipListNode {
        public Integer value;
        // �õ��� ArrayList �ĳ��ȱ�ʾ�ж��ٲ㣬nextNodes[0] ��ʾ��һ���� SkipListNode �ڵ����һ����ʲô
        public ArrayList<SkipListNode> nextNodes;

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
        }
    }

    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        @Override
        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    public static class SkipList {
        // head ���Ǿ�С���������Ĳ�������
        private SkipListNode head;
        private int maxLevel;
        private int size;
        // ��ʲô���ʳ� 0���� 1- ���ʳ� 1
        private static final double PROBABILITY = 0.5;

        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer newValue) {
            if (!contains(newValue)) {
                size++;
                int level = 0;
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                // �����ǰ level ����֮ǰ���� maxLevel����ͷ��һ��Ҫ����
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // ���¼���Ԫ�����Ǵ�ͷ�� head ����߲������ƶ���
                SkipListNode newNode = new SkipListNode(newValue);
                SkipListNode current = head;
                // ͬһ�㣬�����һ��λ�����ȵ�ǰλ����С���������ƶ���������˾������ƶ�
                // ÿ�ζ��Ǵ���߲㿪ʼ������
                int levelAll = maxLevel;
                do {
                    current = findNext(newValue, current, levelAll);
                    if (level >= levelAll) {
                        newNode.nextNodes.add(0, current.nextNodes.get(level));
                        current.nextNodes.set(level, newNode);
                        level--;
                    }
                } while (levelAll-- > 0);
            }
        }

        public void delete(Integer deleteValue) {
            if (contains(deleteValue)) {
                SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode current = head;
                do {
                    current = findNext(deleteNode.value, current, level);
                    if (deleteNode.nextNodes.size() > level) {
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // Returns the skiplist node with greatest value <= e
        private SkipListNode find(Integer e) {
            return find(e, head, maxLevel);
        }

        // Returns the skiplist node with greatest value <= e
        // Starts at node start and level
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;
        }

        // Returns the node at a given level with highest value less than e
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                // �ó� next ֵ
                Integer value = next.value;
                // �����ǰֵС���ó�����ֵ���ҵ���
                if (lessThan(e, value)) {
                    break;
                }
                // �����С�ڣ��������ߣ����� current �����ڸ� level �������һ��С�ڵ�ǰ����
                current = next;
                next = current.nextNodes.get(level);
            }
            return current;
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }

    }

    public static void main(String[] args) {

    }

}
