Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
slow: denotes the pointer just before the dup
fast: denotes the pointer at the last dup
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null){
            while (fast.next != null && fast.val == fast.next.val){
                fast = fast.next;
            }
            if (slow.next != fast ){ //note that we are not only comparing the values
                slow.next = fast.next;
                fast = slow.next;
            }else{
                slow = slow.next;
                fast = fast.next;
            }    
        }
        return dummy.next;
    }
}
