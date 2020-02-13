package TreeDp;

import java.util.ArrayList;
import java.util.List;
/**
 * 一个公司的上下节关系是一棵多叉树，
 * 这个公司要举办晚会，你作为组织者已经摸清了大家的心理：
 * 一个员工的直 接上级如果到场，这个员工肯定不会来
 *每个员工都有一个活跃度的值，决定谁来你会给这个员工发邀请函，怎么 让舞会的气氛最活跃？
 *返回最大的活跃值。
 *
 * @author jasonborn
 *
 */
public class MaxHappy {
	public static class Node {
		public int active;
		public List<Node> nexts;
		public Node(int active) {
			this.active = active;
			nexts = new ArrayList<>();
		}
	}
	public static class ReturnData {
		public int arrive_active;
		public int inarrive_active;
		public ReturnData(int arrive, int inarrive) {
			this.arrive_active = arrive;
			this.inarrive_active = inarrive;
		}
	}
	
	public static ReturnData maxHappy(Node head) {
		int arrive_act = head.active;
		int inarrive_act = 0;
		for(int i = 0; i < head.nexts.size(); i++) {
			Node next = head.nexts.get(i);
			ReturnData nextData = maxHappy(next);
			arrive_act += nextData.inarrive_active;
			inarrive_act += Math.max(nextData.arrive_active, nextData.inarrive_active);
		}
		return new ReturnData(arrive_act, inarrive_act);
	}
	public static void main(String[] args) {
		Node head = new Node(8);
		Node head_below1 = new Node(9);
		Node head_below2 = new Node(10);
		Node head_below3 = new Node(100);		
		head.nexts.add(head_below1);
		head.nexts.add(head_below2);
		head.nexts.add(head_below3);
		head.nexts.get(0).nexts.add(new Node(3));
		head.nexts.get(0).nexts.add(new Node(5));
		head.nexts.get(0).nexts.add(new Node(1));
		head.nexts.get(1).nexts.add(new Node(5));
		head.nexts.get(1).nexts.add(new Node(6));
		head.nexts.get(1).nexts.add(new Node(9));
		head.nexts.get(1).nexts.add(new Node(4));
		head.nexts.get(2).nexts.add(new Node(3));
		head.nexts.get(2).nexts.add(new Node(2));
		head.nexts.get(2).nexts.add(new Node(1));
		int maxH = Math.max(maxHappy(head).arrive_active, maxHappy(head).inarrive_active);
		System.out.println(maxH);
		
	}
}
