A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Have you met this question in a real interview? Yes
Example
Challenge 
Could you solve it with O(1) space?



/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return head;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode newHead = dummy;
        RandomListNode t = head;
        
        while (head != null){
            RandomListNode node = new RandomListNode(head.label);
            map.put(head, node); 
            newHead.next = node;
            newHead = node;
            head = head.next;
        }

        head = t;
        while (head != null){
            RandomListNode temp = map.get(head);
            temp.random = map.get(head.random);
            head = head.next;
        }
        return dummy.next;
    }
}
