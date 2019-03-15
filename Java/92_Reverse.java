
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        dummy.next = head;
        int i = 0;
        while (node != null){
            if (i == m - 1){
                break;
            }
            i++;
            node = node.next;
        }
        ListNode first = node;
        node = node.next;
        ListNode last = node;
        ListNode prev = null;
        while (node != null){
            ListNode temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
            i++;
            if (i == n){
                break;
            } 
        }
        first.next = prev;
        last.next = node;
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        dummy.next = head;
        int i = 0;
        while (node != null){
            if (i == m - 1){
                break;
            }
            i++;
            node = node.next;
        }
        ListNode first = node;
        node = node.next;
        i++;
        ListNode last = node;
        ListNode prev = null;
        while (node != null){
            ListNode temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
            i++;
            if (i == n+1){
                break;
            } 
        }
        first.next = prev;
        last.next = node;
        return dummy.next;
    }
}
