Given a singly linked list, return a random node's value from the linked list. Each node must have the same 
probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently
without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

Method 1: Calculate length first
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode node;
    private Random random;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        node = head;
        random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = node;
        int len = 0;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        int targetIndex = random.nextInt(len);
        cur = node;
        int i = 0;
        while (cur != null && i < targetIndex){
            cur = cur.next;
            i++;
        }
        return cur.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
 
 Method 2 (good): don't calculate length  (reveroir sampling)
 https://leetcode.com/problems/linked-list-random-node/discuss/85662/Java-Solution-with-cases-explain
 http://blog.jobbole.com/42550/
 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private ListNode head;
    private Random random;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = head;
        int i = 0;
        int ans = cur.val;
        while (cur != null){
            if (random.nextInt(i + 1) == i){
                ans = cur.val;
            }
            cur = cur.next;
            i++;
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
 
 
