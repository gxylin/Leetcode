Sort a linked list using insertion sort.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null){
            if (cur.val >= prev.val){
                prev = cur;
                cur = cur.next;
                continue;
            }
            ListNode node = dummy;
            while (cur.val > node.next.val){
                node = node.next;
            }
            ListNode temp = cur.next;
            cur.next = node.next;
            node.next = cur;
            cur = temp;
            prev.next = temp;
        }
        return dummy.next;
    }
}
