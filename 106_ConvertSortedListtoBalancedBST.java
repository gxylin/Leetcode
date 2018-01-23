Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Have you met this question in a real interview? Yes
Example
               2
1->2->3  =>   / \
             1   3
             
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 * Definition of TreeNode:
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
    /*
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
       if (head == null){
           return null;
       }
       return helper(head, null);
    }
    private TreeNode helper(ListNode head, ListNode tail){
        if (head == tail){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != tail && fast.next != tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }
}
