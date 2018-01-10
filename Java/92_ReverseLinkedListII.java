Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
 Method:
 since the first node could be reversed, dummy is needed and then start from 0.
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        for (int i = 0; i < m - 1; i++){
            cur = cur.next;
        }
        ListNode start = cur;
        ListNode tail = cur.next;
        int i = m;
        ListNode prev = null;
        cur = cur.next;
        while (cur != null && i <= n){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            i++;
        }
        start.next = prev;
        tail.next = cur;
        return dummy.next;
    }
}
