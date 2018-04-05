Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode next = head.next;
       
        while (cur != null && next != null){
            ListNode temp = next.next;
            next.next = cur;
            cur.next = temp;
            prev.next = next;
            prev = cur;
            cur = temp;
            if (temp != null){
                next= temp.next;
            }else{
                next = null;
            }   
        }
        return dummy.next;
    }
}
