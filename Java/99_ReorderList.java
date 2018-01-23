Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln

reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

Have you met this question in a real interview? Yes
Example
Given 1->2->3->4->null, reorder it to 1->4->2->3->null.

Challenge 
Can you do this in-place without altering the nodes' values?

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
 */


public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        if (head == null){
            return;
        }
        ListNode mid = findMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;
        merge(head, tail);
    }
    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curt = head;
        while (curt != null){
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        return prev;
    }
    private void merge(ListNode head1, ListNode head2){
        ListNode lastNode = new ListNode(0);
        int index = 0;
        while (head1 != null && head2 != null){
            if (index %2 == 0){
                lastNode.next = head1;
                lastNode = head1;
                head1 = head1.next;
            }else{
                lastNode.next = head2;
                lastNode = head2;
                head2 = head2.next;
            }
            index++;
        }
        if (head1 == null){
            lastNode.next = head2;
        }else{
            lastNode.next = head1;
        }
    }
}
