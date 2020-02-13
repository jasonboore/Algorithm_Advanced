package TreeDp;

/**
 *二叉树中，一个节点可以往上走和往下走，那么从节点A总能走到节点 B。
 *节点A走到节点B的距离为：A走到B最短路径上的节点个数。
 *求一棵二叉树上的最远距离
 * @author jasonborn
 *
 */
public class MaxDistanceInTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	public static class ReturnData {
		public int distance;
		public int h;
		public ReturnData(int distance, int h) {
			this.distance = distance;
			this.h = h;
		}
	}
	public static int maxDistanceInTree(Node head) {
		if(head == null) {
			return 0;
		}
		return process(head).distance;
	}
	private static ReturnData process(Node head) {
		if(head == null) {
			return new ReturnData(0, 0);
		}
		ReturnData leftData = process(head.left);
		ReturnData rightData = process(head.right);
		int leftDis = leftData.distance;
		int rightDis = rightData.distance;
		int itselfDis = leftData.h + rightData.h + 1;
		int height = Math.max(leftData.h, rightData.h) + 1;
		int maxDis = Math.max(itselfDis, Math.max(leftDis, rightDis));
		return new ReturnData(maxDis, height);
	}
	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistanceInTree(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistanceInTree(head2));

	}
}
