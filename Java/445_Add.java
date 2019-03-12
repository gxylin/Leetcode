You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Method 1: reverse
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
        l1 = reverse(l1);
        l2 = reverse(l2);
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
        return reverse(dummy.next);
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

Method 2: no revserse
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
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (l1 != null){
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null){
            s2.push(l2);
            l2 = l2.next;
        }
        int sum = 0;
        int carry = 0;
        ListNode node = new ListNode(-1);
        while (!s1.isEmpty() || !s2.isEmpty()){
            sum = carry;
            if (!s1.isEmpty()){
                sum += s1.pop().val;
            }
            if (!s2.isEmpty()){
                sum += s2.pop().val;
            }
            carry = sum / 10;
            node.val = sum % 10;
            ListNode head = new ListNode(carry);
            head.next = node;
            node = head;
        }
        return node.val != 0 ? node : node.next;
    }
}


Better:
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
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode n1 = l1;
        while (n1 != null){
            s1.push(n1.val);
            n1 = n1.next;
        }
        ListNode n2 = l2;
        while (n2 != null){
            s2.push(n2.val);
            n2 = n2.next;
        }
        int carry = 0;
        int sum = 0;
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!s1.isEmpty() && !s2.isEmpty()){
            sum = carry + s1.pop() + s2.pop();
            ListNode node = new ListNode(sum%10);
            carry = sum/10;
            node.next = head.next;
            head.next = node;
        }
        while (!s1.isEmpty()){
            sum = carry + s1.pop();
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
        }
        while (!s2.isEmpty()){
            sum = carry + s2.pop();
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
        }
        if (carry == 0){
            return dummy.next;
        }
        ListNode node = new ListNode(carry);
        node.next = head.next;
        head.next = node;
        return dummy.next;
    }
}
