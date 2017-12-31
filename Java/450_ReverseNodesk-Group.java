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
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1){
            return head;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        int n = len / k;
        if (n == 0){
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode dummy2 = dummy;
        for (int j = 0; j < n; j++){
            ListNode prev = null;
            cur = head;
            for (int i = 0 ; i < k; i++){
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            dummy.next = prev;
            dummy = head;
            head.next = cur;
            head = cur;
        }
        return dummy2.next;
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
