Sort a linked list in O(n log n) time using constant space complexity.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode right = sortList(middle.next);
        middle.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }
    private ListNode findMiddle(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                lastNode.next = l2;
                l2 = l2.next;
            }else{
                lastNode.next = l1;
                l1 = l1.next;
            }
            lastNode = lastNode.next;
        }
        if (l1 != null){
            lastNode.next = l1;
        }
        if (l2 != null){
            lastNode.next = l2;
        }
        return dummy.next;
    }
}
