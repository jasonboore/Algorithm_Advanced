package WriteBySelf;

import java.util.HashMap;

/**
 * ��ƿ��Ա���Ļ���ṹ��LRU�� 
 * ����Ŀ�� 
 * ���һ�ֻ���ṹ���ýṹ�ڹ���ʱȷ����С��
 * �����СΪK�������������ܣ�
 *  1.set(key,value)������¼(key,value)����ýṹ�� 
 *  2.get(key)������key��Ӧ��valueֵ�� 
 *  ��Ҫ�� 
 *  1��set��get������ʱ�临�Ӷ�ΪO(1)��
 *  2��ĳ��key��set��get����һ����������Ϊ���key�ļ�¼�������ʹ�õġ�
 *  3��������Ĵ�С����Kʱ���Ƴ������ʹ�õļ�¼����set��get���Զ�ġ� 
 * @author jasonborn
 *
 */
public class LRU {
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
		public Node<K, V> head;
		public Node<K, V> tail;
		
		public NodeDoubleLinkedList() {
			this.head = null;
			this.tail = null;
		}
		
		public void addNode(Node<K, V> node) {
			if(node == null) {
				return;
			}
			if(this.head == null) {
				this.head = node;
				this.tail = node;
			}else {
				this.tail.next = node;
				node.last = this.tail;
				this.tail = node;
			}
		}
		public void moveNodeToTail(Node<K, V> node) {
			if(node == this.tail) {
				return;
			}
			if(node == this.head) {
				this.head = head.next;
				this.head.last = null;
			}else {
				node.next.last = node.last;
				node.last.next = node.next;
			}
			node.last = this.tail;
			node.next = null;
			this.tail.next = node;
			this.tail = node;
		}
		public Node<K, V> removeHead() {
			if(head == null) {
				return null;
			}
			Node<K, V> head = this.head;
			if(this.head == this.tail) {
				this.head = null;
				this.tail = null;
			}else {
				this.head = head.next;
				head.next = null;
				this.head.last = null;
			}
			return head;
		}
	}
	 public static class MyCache<K, V> {
		 public int capacity;
		 HashMap<K, Node<K, V>> map;
		 NodeDoubleLinkedList<K, V> doubleLinkedList;
		 
		 public MyCache(int capacity) {
			 if(capacity < 1) {
				 throw new RuntimeException("should be more than 0.");
			 }
			 map = new HashMap<>();
			 doubleLinkedList = new NodeDoubleLinkedList<>();
			 this.capacity = capacity;
			 }
		 public V get(K key) {
			 if(map.containsKey(key)) {
				 Node<K, V> node = map.get(key);
				 doubleLinkedList.moveNodeToTail(node);
				 return node.value;
			 }else {
				 return null;
			 }
		 }
		 public void set(K key, V value) {
			 if(map.containsKey(key)) {
				 Node<K, V> node = this.map.get(key);
				 node.value = value;
				 doubleLinkedList.moveNodeToTail(node);
			 }else {
				 Node<K, V> node = new Node<>(key, value);
				 map.put(key, node);
				 doubleLinkedList.addNode(node);
				 if(this.map.size() == this.capacity + 1) {
					 this.removeMostUnusedCache();
				 }
			 }
		 }
		private void removeMostUnusedCache() {
			Node<K, V> head = doubleLinkedList.removeHead();
			K key = head.key;
			map.remove(key);	
		}
	}
	  public static void main(String[] args) {
	       MyCache<String, Integer> cache = new MyCache<>(4);
	       cache.set("A", 1);
	       cache.set("B", 11);
	       cache.set("C", 111);
	       cache.set("D", 1111);
	       System.out.println(cache.get("C"));
	       System.out.println(cache.get("B"));
	       System.out.println(cache.get("A"));
	       cache.set("E", 11111);
	       cache.set("B", 2);
	       System.out.println(cache.get("B"));
	       System.out.println(cache.get("A"));
	       System.out.println(cache.get("D"));
	      
	    }
}
