Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Method 1:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null){
            return head;
        }
        ListNode node = head;
        int len = 0;
        while (node != null){
            node = node.next;
            len++;
        }
        int n = len/k;
        if (n == 0){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode dummyNode = dummy;
        for (int i = 0; i < n; i++){
            ListNode prev = null;
            node = head;
            for (int j = 0; j < k; j++){
                ListNode temp = node.next;
                node.next = prev;
                prev = node;
                node = temp;
            }
            dummyNode.next = prev;
            head.next = node;
            dummyNode = head;
            head = node;
        }
        return dummy.next;
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


public class Solution {
    /*
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while (true){
            head = reverseK(head, k);
            if (head == null){
                break;
            }
        }
        return dummy.next;
    }
    private ListNode reverseK(ListNode head, int k){
        ListNode nk = head;
        for (int i = 0; i < k; i++){
            if (nk == null){
                return null;
            }
            nk = nk.next;
        }
        
        if (nk == null){
            return null;
        }
        ListNode nkplus = nk.next;
        ListNode n1 = head.next;
        //reverse link
        ListNode prev = null;
        ListNode cur = n1;
        
        while (cur != nkplus){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        
        head.next = nk;
        n1.next = nkplus;
        
        return n1;
        
    }
    
}
