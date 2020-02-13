package nowcoder.advanced.day01;



public class MorrisTraversal {
    public static void process(Node head) {
        if (head == null) {
            return;
        }
        // 1����ӡ��������Ϊ�������
        //System.out.println(head.value);
        process(head.left);
        // 2����ӡ��������Ϊ�������
        System.out.print(head.value + " ");
        process(head.right);
        // 3,��ӡ��������Ϊ�������(�����λص��Լ��ڵ�ʱ���ӡ)
        //System.out.println(head.value);
    }

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // Morris �������
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        // ��ʼ cur ָ�� head
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // �ڵ� cur ���Ӳ�Ϊ����������
            if (mostRight != null) {
                // ���������������ҵĽڵ�
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // ������ĵ�һ�ֿ�����
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                    // ������ĵڶ��ֿ�����
                } else {
                    mostRight.right = null;
                }
            }
            // �ڽڵ�������֮ǰ��ӡ
            System.out.print(cur.value + " ");
            // ��û�����ӵ�ʱ��
            cur = cur.right;
        }
        System.out.println();
    }

    // Morris ������Ϊ�������
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
                // �� else ��ʾ��ǰ���û����������ʱ�򣬿�����Ϊ���һ�ε���͵ڶ��ε���������һ���
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    // Morris ʵ�ֺ�������
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // if ��ʾһ���ڵ���Իص��Լ�����
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                    // �����ǻص��Լ�����
                } else {
                    mostRight.right = null;
                    // ��ӡ��������������ұ߽������ӡ
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        // ��������֮ǰ��ӡ�������ұ߽�
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
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
//		System.out.print("pre-order: ");
//		process(head);
//		System.out.println();
//		morrisIn(head);
		System.out.print("in-order: ");
		process(head);
		System.out.println();
		morrisIn(head);
//		System.out.print("pos-order: ");
//		posOrderRecur(head);
//		System.out.println();
//
//		// unrecursive
//		System.out.println("============unrecursive=============");
//		preOrderUnRecur(head);
//		inOrderUnRecur(head);
//		posOrderUnRecur1(head);
//		posOrderUnRecur2(head);
    }
}