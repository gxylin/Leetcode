Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Method 1: iteration
class Solution {
    public ListNode reverseList(ListNode head) {
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

Method 2: recurstion
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }
    ListNode reverse(ListNode head, ListNode newHead){
        if (head == null){
            return newHead;
        }
        ListNode temp = head.next;
        head.next = newHead;
        return reverse(temp, head);
    }
}
