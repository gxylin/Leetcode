onvert a binary search tree to doubly linked list with in-order traversal.

Have you met this question in a real interview? Yes
Example
Given a binary search tree:

    4
   / \
  2   5
 / \
1   3
return 1<->2<->3<->4<->5

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        if (root == null){
            return null;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        traverse(root, queue);
        
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode head = dummy;
        while (!queue.isEmpty()){
            DoublyListNode temp = new DoublyListNode(queue.poll());
            head.next = temp;
            temp.prev = head;
            head = head.next;
        }
        return dummy.next;
    }
    private void traverse(TreeNode root, Queue<Integer> queue){
        if (root == null){
            return;
        }
        traverse(root.left, queue);
        queue.offer(root.val);
        traverse(root.right,queue);
    }
}



Iteration:
/**
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * } * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        DoublyListNode dummy = new DoublyListNode(-1);
        DoublyListNode prev = dummy;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            TreeNode curr = stack.pop();
            DoublyListNode temp = new DoublyListNode(curr.val);
            prev.next = temp;
            temp.prev = prev;
            prev = temp;
            node = curr.right;
        }
        return dummy.next;
    }
}
