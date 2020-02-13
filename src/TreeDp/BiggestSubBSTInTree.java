package TreeDp;

/**
 * 给定一个二叉树的头节点head，请返回最大搜索二叉子树的大小
 * @author jasonborn
 *
 */
public class BiggestSubBSTInTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	public static class ReturnData {
		public int size;
		public Node head;
		public int minValue;
		public int maxValue;
		
		public ReturnData(int size, Node head, int minValue, int maxValue) {
			this.size = size;
			this.head = head;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
		public static ReturnData process(Node head) {
			if(head == null) {
				return new ReturnData(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
			}
			ReturnData leftData = process(head.left);
			ReturnData	rightData = process(head.right);
			
			int itselfSize = 0;
			if(head.left == leftData.head && 
			   head.right == rightData.head && 
			   head.value > leftData.maxValue && 
			   head.value < rightData.minValue) {
				itselfSize = leftData.size + rightData.size + 1;
			}
			int leftSize = leftData.size;
			int rightSize = rightData.size;
			int maxSize = Math.max(itselfSize, Math.max(leftSize, rightSize));
			Node maxHead = leftSize > rightSize ? leftData.head : rightData.head;
			if(maxSize == itselfSize) {
				maxHead = head;
			}
			int minValue = Math.min(head.value, Math.min(leftData.minValue, rightData.minValue));
			int maxValue = Math.max(head.value, Math.max(leftData.maxValue, rightData.maxValue));
			return new ReturnData(maxSize, maxHead, minValue, maxValue);
		}
		 public static Node biggestSubBST(Node head) {
		        int[] record = new int[3]; // 0->size, 1->min, 2->max
		        return posOrder(head, record);
		    }
		 public static Node posOrder(Node head, int[] record) {
		        if (head == null) {
		            record[0] = 0;
		            record[1] = Integer.MAX_VALUE;
		            record[2] = Integer.MIN_VALUE;
		            return null;
		        }
		        int value = head.value;
		        Node left = head.left;
		        Node right = head.right;
		        Node lBST = posOrder(left, record);
		        int lSize = record[0];
		        int lMin = record[1];
		        int lMax = record[2];
		        Node rBST = posOrder(right, record);
		        int rSize = record[0];
		        int rMin = record[1];
		        int rMax = record[2];
		        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
		        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
		        if (left == lBST && right == rBST && lMax < value && value < rMin) {
		            record[0] = lSize + rSize + 1;
		            return head;
		        }
		        record[0] = Math.max(lSize, rSize);
		        return lSize > rSize ? lBST : rBST;
		    }
		 public static void printTree(Node head) {
		        System.out.println("Binary Tree:");
		        printInOrder(head, 0, "H", 17);
		        System.out.println();
		    }

		    public static void printInOrder(Node head, int height, String to, int len) {
		        if (head == null) {
		            return;
		        }
		        printInOrder(head.right, height + 1, "v", len);
		        String val = to + head.value + to;
		        int lenM = val.length();
		        int lenL = (len - lenM) / 2;
		        int lenR = len - lenM - lenL;
		        val = getSpace(lenL) + val + getSpace(lenR);
		        System.out.println(getSpace(height * len) + val);
		        printInOrder(head.left, height + 1, "^", len);
		    }

		    public static String getSpace(int num) {
		        String space = " ";
		        StringBuffer buf = new StringBuffer("");
		        for (int i = 0; i < num; i++) {
		            buf.append(space);
		        }
		        return buf.toString();
		    }

		    public static void main(String[] args) {

		        Node head = new Node(6);
		        head.left = new Node(1);
		        head.left.left = new Node(0);
		        head.left.right = new Node(3);
		        head.right = new Node(12);
		        head.right.left = new Node(10);
		        head.right.left.left = new Node(4);
		        head.right.left.left.left = new Node(2);
		        head.right.left.left.right = new Node(5);
		        head.right.left.right = new Node(14);
		        head.right.left.right.left = new Node(11);
		        head.right.left.right.right = new Node(15);
		        head.right.right = new Node(13);
		        head.right.right.left = new Node(20);
		        head.right.right.right = new Node(16);

		        printTree(head);
		        Node bst = biggestSubBST(head);
		        printTree(bst);
		        printTree(process(head).head);
		        System.out.println(process(head).size);

		    }
	}
}
