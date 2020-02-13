package WriteBySelf;



/**
 * �����ĵ�ǰ�ڵ㼸λcur
 * 1.���cur�����ӣ�cur�����ƶ���cur = cur��right��
 * 2.���cur�����ӣ��ҵ�cur�����������ҵĽڵ㣬��Ϊmostright
 *   ��1�����mostright��rightָ��ָ��գ�����ָ��cur��cur�����ƶ���cur = cur.left��
 *   ��2�����mostrightָ��cur������ָ�ؿգ�cur�����ƶ�
 * @author jasonborn
 *
 */
public class MorrisTraversal {
	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	public static void morrisPre(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur != null) {
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					System.out.print(cur.value + " ");
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
				}
			}else {
				System.out.print(cur.value + " ");
			}
			cur = cur.right;
		}
		System.out.println();
	}
	public static void morrisIn(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur != null) {
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
				}
			}
			System.out.print(cur.value + " ");
			cur = cur.right;
			
		}
		System.out.println();
	}
	public static void morrisPos(Node head) {
		if(head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while(cur != null) {
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				}else {
					mostRight.right = null;
					printEdge(cur.left);
				}
			}
			
			cur = cur.right;
			
		}
		printEdge(head);
		System.out.println();
	}
	 private static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while(cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}
	private static Node reverseEdge(Node head) {
		Node pre = null;
		Node next = null;
		while(head != null) {
			next = head.right;
			head.right = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	public static void main(String[] args) {
	    	Node head = new Node(5);
			head.left = new Node(3);
			head.right = new Node(8);
			head.left.left = new Node(2);
			head.left.right = new Node(4);
			head.left.left.left = new Node(1);
			head.right.left = new Node(7);
			head.right.left.left = new Node(6);
			head.right.right = new Node(10);
			head.right.right.left = new Node(9);
			head.right.right.right = new Node(11);

			// recursive
			System.out.println("==============recursive==============");
			System.out.print("pre-order: ");
			System.out.println();
			preOrderRecur(head);
			System.out.println();
			morrisPre(head);
			System.out.print("in-order: ");
			System.out.println();
			inOrderRecur(head);
			System.out.println();
			morrisIn(head);
			System.out.print("pos-order: ");
			System.out.println();
			posOrderRecur(head);
			System.out.println();
			morrisPos(head);

	    }
}
