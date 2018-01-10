Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode l1 = dummy1;
        ListNode l2 = dummy2;
        int count = 1;
        ListNode curr = head;
        while (curr != null){
            ListNode temp = curr.next;
            if (count % 2 == 1){
                l1.next = curr;
                l1 = curr;  
                l1.next = null;
            }else{
                l2.next = curr;
                l2 = curr;
                l2.next = null;
            }
            count++;
            curr = temp;
        }
        l1.next = dummy2.next;
        return dummy1.next;
    }
}
