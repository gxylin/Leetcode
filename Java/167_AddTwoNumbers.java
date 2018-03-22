You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Have you met this question in a real interview? Yes
Example
Given 7->1->6 + 5->9->2. That is, 617 + 295.

Return 2->1->9. That is 912.

Given 3->1->5 and 5->9->2, return 8->0->8.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */

The same as array addition

public class Solution {
    /*
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int carry = 0;
        for (ListNode i = l1, j = l2; i != null || j != null;){
            int sum = carry;
            sum += (i != null) ? i.val : 0;
            sum += (j != null) ? j.val : 0;
            ListNode newNode = new ListNode(sum % 10);
            tail.next = newNode;
            tail = newNode;
            carry = sum / 10;
            
            i = (i != null) ? i.next : i;
            j = (j != null) ? j.next : j;
        }
        if (carry != 0){
             tail.next = new ListNode(carry);
        }
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        ListNode n1= l1;
        ListNode n2 = l2;
        int carry = 0;
        int sum = 0;
        while (n1 != null || n2 != null){
            sum = carry;
            sum += (n1 != null) ? n1.val : 0;
            sum += (n2 != null) ? n2.val : 0;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            head.next = node;
            head = node;
            n1 = (n1 != null) ? n1.next : n1;
            n2 = (n2 != null) ? n2.next : n2;
        }
        if (carry != 0){
            head.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
