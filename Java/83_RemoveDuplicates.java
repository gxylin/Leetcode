Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
    
Method 1:
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null){
            if (slow.val != fast.val){
                slow.next = fast;
                slow = fast;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;      
    }
}

Method 2:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
}
