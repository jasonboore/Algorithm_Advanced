package WriteBySelf;

import java.util.HashMap;

public class LFU {

    // �������Ź��ŵ�С����ṹ
    public static class Node {
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {
        // ���������Ӧ��ͷ�����������ŵ�����ṹ����������� Node �ڵ�
        public static class NodeList {
            public Node head;
            public Node tail;
            public NodeList last;
            public NodeList next;

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            public void addNodeFromHead(Node newHead) {
                newHead.down = head;
                head.up = newHead;
                head = newHead;
            }

            public boolean isEmpty() {
                return head == null;
            }

            // ɾ�� NodeList ��һ������������һ����㣬�����м�ĳ�����ʹ�� get ����֮�� times + 1�����ӵ�ǰ NodeList ��ɾ��Ȼ��ӵ���һ��
            //NodeList �С�
            public void deleteNode(Node node) {
                // ��ʣΨһһ������ʱ��
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    // ���Ϊͷָ�룬��ͷ
                    if (node == head) {
                        head = node.down;
                        head.up = null;
                        // ���Ϊβָ�룬��β
                    } else if (node == tail) {
                        tail = node.up;
                        tail.down = null;
                        // ���Ϊ�м�ڵ㣬�򽫸ý�����½��ָ�����Ӻü���
                    } else {
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                // ��������������������ɾ��
                node.up = null;
                node.down = null;
            }
        }


        private int capacity;
        private int size;
        // key��Integer����Ӧһ�� Node
        private HashMap<Integer, Node> records;
        private HashMap<Node, NodeList> heads;
        // ��¼������˫������ NodeList ��ͷ������Ϊͷ������һ�����Ǵ�ƵΪ 1 �� NodeList����Ϊ�����ƵΪ 1 �� Node ��û�У���������ƵΪ 1
        //�� NodeList Ҳ��ɾ���ˡ�
        private NodeList headList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
            headList = null;
        }

        public void set(int key, int value) {
            // ����� key ���ڣ�ͨ���� key �� Node ���ڴ��ַ�ó����Ϳ��Է��ʵ� Node �ṹ
            if (records.containsKey(key)) {
                Node node = records.get(key);
                node.value = value;
                node.times++;
                // �� Node ����ԭ�����ĸ� NodeList
                NodeList curNodeList = heads.get(node);
                // ���� Node �ƶ���ԭ�� NodeList + 1 λ���ϣ�
                move(node, curNodeList);
            } else {
                // ����ﵽ��������Ҫ������ NodeList ��ͷ���Ĵ� List ������ص�β���ɾ��
                if (size == capacity) {
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    // �Ƴ�Ԫ��֮���漰��ԭ�� NodeList �п���û��Ԫ�ػ�ͷ���Լ������Ԫ��ҲҪ��ͷ���⣨ֻԭ����С�� 2 �γ��֣�Ȼ���¼�һ��
                    //Node��������Ҫ������ƵΪ 1 �� NodeList����Ҫ��ԭ��ָ�� 3  �� headList ָ�� 1��
                    modifyHeadList(headList);
                    // �����ý��Ӱ��
                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }
                // ���û�и�Ԫ�أ��½� Node ���Ҹ�ֵ
                Node node = new Node(key, value, 1);
                // ���û�� headList���½����� Node ����
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    // ��� NodeList ���иô�Ƶ������ֱ�ӹ������漴��
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        // �½���ͷ NodeList��Ȼ�����
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        private void move(Node node, NodeList oldNodeList) {
            // ����������ɾ���� Node
            oldNodeList.deleteNode(node);
            // ԭ NodeList Ϊ 3 - 4 - 6 �����get NodeList 4 ����Ԫ�أ���� get ֮����Ԫ�أ������� 4 �� 6 ֱ���½� 5��Ȼ��4 -
            //5 -6����� get ֮��û��Ԫ���ˣ����½� 5��Ȼ���� 3 - 5 - 6
            // preList �� oldNodeList ��ǰһ������� modifyHeadList����Ϊ�棬���µ������ǰ�� NodeList Ϊ�������ǰһ��
            //NodeList��������� oldNodeList ����
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
                    : oldNodeList;
            NodeList nextList = oldNodeList.next;
            // ��� nextList Ϊ�գ��� oldNodeList ���������������β��
            if (nextList == null) {
                // �½� NodeList��Ȼ����� Node
                NodeList newList = new NodeList(node);
                // ����������
                if (preList != null) {
                    preList.next = newList;
                }
                newList.last = preList;
                if (headList == null) {
                    headList = newList;
                }
                // Node �����µ� NodeList ��
                heads.put(node, newList);
            } else {
                // ԭ���������ڵ�ǰ��Ƶ�� + 1
                if (nextList.head.times.equals(node.times)) {
                    nextList.addNodeFromHead(node);
                    heads.put(node, nextList);
                } else {
                    // �½�һ�� +1 ��Ƶ�� NodeList��Ȼ������
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        // Node ɾ��֮���ж���� NodeList �Ƿ�Ҳ��Ҫɾ��
        private boolean modifyHeadList(NodeList nodeList) {
            // ��� NodeList ��Ϊ�գ�ֱ�ӷ��� false�����Ϊ������Ҫɾ��
            if (nodeList.isEmpty()) {
                // ����� NodeList �����������������ͷ����
                if (headList == nodeList) {
                    // ���Ƚ�ͷָ����ͷ������һ��
                    headList = nodeList.next;
                    // �����Ϊ�գ������ headList �Ѿ�����ͷ���ˣ�����ǰָ���
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    // ����Ǵ������м�Ԫ�أ������� NodeList ǰ��ֱ�����Ӽ���
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        public int get(int key) {
            // �����ھͷ��ؿ�
            if (!records.containsKey(key)) {
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
            return node.value;
        }
    }
}
