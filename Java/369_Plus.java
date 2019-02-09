Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:

Input:
1->2->3

Output:
1->2->4


Method 1: reverse + plus one for array (66_PlusOne.java)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode reverseHead = reverse(head);
        ListNode node = reverseHead;
        while (node != null){
            if (node.val < 9){
                node.val += 1;
                return reverse(reverseHead);
            }
            node.val = 0;
            node = node.next;
        }
        ListNode newHead = new ListNode(1);
        newHead.next = reverseHead;
        return newHead;
    }
    private ListNode reverse(ListNode head){
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

Method 2:
how to add value reversely: Use DFS
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null){
            return head;
        }
        if (dfs(head) == 0){
            return head;
        }else{
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
    }
    private int dfs(ListNode node){
        if (node == null){
            return 1;
        }
        int carry = dfs(node.next);
        if (carry == 0){
            return 0;
        }
        int val = node.val + 1;
        node.val = val % 10;
        return val / 10;
    }
}

Best solution:
class Solution {
    public ListNode plusOne(ListNode head){
         if (head == null){
             return head;
         }
         int carry = dfs(head);
         if (carry == 0){
            return head;
         }
         ListNode newHead = ListNode(1);
         newHead.next = head;
         return newHead;
    }
    private int dfs(ListNode node){
        if (node == null){
            return 1;
        }
        int carry = dfs(node.next);
        if (carry == 0){
            return 0;
        }
        int val = node.val + carry;
        node.val = val%10;
        return val/10;
    }
}


Method 3:
Iterative Two-Pointers with dummy node Java O(n) time, O(1) space

    i stands for the most significant digit that is going to be incremented if there exists a carry
    dummy node can handle cases such as "9->9>-9" automatically


public class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;
        
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }
        
        if (j.val != 9) {
            j.val++;
        } else {
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }
        
        if (dummy.val == 0) {
            return dummy.next;
        }
        
        return dummy;
    }
}
