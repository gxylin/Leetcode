Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:

Input:
1->2->3

Output:
1->2->4


Method 1: reverse + plus one for array (66_PlusOne.java)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode reverseHead = reverse(head);
        ListNode node = reverseHead;
        while (node != null){
            if (node.val < 9){
                node.val += 1;
                return reverse(reverseHead);
            }
            node.val = 0;
            node = node.next;
        }
        ListNode newHead = new ListNode(1);
        newHead.next = reverseHead;
        return newHead;
    }
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}

