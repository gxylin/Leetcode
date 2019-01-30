Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. 
It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, 
do nothing.

 Notice
You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->4->null and v1 = 2, v2 = 4.

Return 1->4->3->2->null.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param head: a ListNode
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
       ListNode dummy = new ListNode(-1);
       dummy.next = head;
       ListNode prev = dummy;
       ListNode curt = head;
       int count = 0;
       ListNode v1Prev = dummy, v2Prev = dummy, v1Curt = dummy, v2Curt = dummy;
       
       while (curt != null){
           if (curt.val == v1){
               v1Prev = prev;
               v1Curt = curt;
               count++;
           }
           if (curt.val == v2){
               v2Prev = prev;
               v2Curt = curt;
               count++;
           }
           if (count == 2){
               v1Prev.next = v2Curt;
               v2Prev.next = v1Curt;
               ListNode temp = v1Curt.next;
               v1Curt.next = v2Curt.next;
               v2Curt.next = temp;
               break;
           }
           prev = curt;
           curt = curt.next;
       }
       return dummy.next;
    }
}


class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode next = head.next;
       
        while (cur != null && next != null){
            ListNode temp = next.next;
            next.next = cur;
            cur.next = temp;
            prev.next = next;
            prev = cur;
            cur = temp;
            if (temp != null){
                next= temp.next;
            }
        }
        return dummy.next;
    }
}
