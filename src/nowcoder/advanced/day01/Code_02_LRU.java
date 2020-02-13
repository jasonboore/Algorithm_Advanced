package nowcoder.advanced.day01;
import java.util.HashMap;

public class Code_02_LRU {

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        // �Զ���˫��������ӽ�����
        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        // �������Ľڵ��ƶ��������β��
        public void moveNodeToTail(Node<K, V> node) {
            if (this.tail == node) {
                return;
            }
            // ͷ��㴦��
            if (this.head == node) {
                this.head = node.next;
                this.head.last = null;
                // ��ͨ��㴦��
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        // �Ƴ�ͷ�����
        public Node<K, V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            // ֻ��һ����������
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
                // ��ֹһ�����������
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    /**
     * ע�⣺ע�⣺ע�⣺�� map<key, value> �� key �� value �ǻ����������ͻ��� string ���͵�ʱ��map
     * �д�ŵ���ֵ��������Զ����������ͣ���ŵ����ڴ��ַ��һ�����ã���
     *
     * @param <K>
     * @param <V>
     */
    public static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            this.keyNodeMap = new HashMap<K, Node<K, V>>();
            this.nodeList = new NodeDoubleLinkedList<K, V>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> res = this.keyNodeMap.get(key);
                // ������õ� Node ֮��Ȼ��������˫�������н� Node �ƶ���β��
                this.nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> node = this.keyNodeMap.get(key);
                // ���ý��ֵ��ֵΪ��ֵ
                node.value = value;
                // ���� set�������ȼ��ᵽ���
                this.nodeList.moveNodeToTail(node);
            } else {
                // ���û�иý�㣬�½� Node��Ȼ��װ�� Key - value
                Node<K, V> newNode = new Node<K, V>(key, value);
                // �� Map ��˫�������м���ý��
                this.keyNodeMap.put(key, newNode);
                this.nodeList.addNode(newNode);
                // ������� map ��С���ˣ��Ƴ�β���Ľڵ�
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUnusedCache();
                }
            }
        }

        // ��������ɾ��β���������½ڵ�
        private void removeMostUnusedCache() {
            // ɾ�� Node ��˫��������λ��
            Node<K, V> removeNode = this.nodeList.removeHead();
            // ɾ�� Map �нڵ�
            K removeKey = removeNode.key;
            this.keyNodeMap.remove(removeKey);
        }

    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        testCache.set("B", 333);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("C"));

    }

}
