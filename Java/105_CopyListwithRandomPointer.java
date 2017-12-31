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
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return head;
        }
        
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        
        RandomListNode temp = head;
        RandomListNode newNode = dummy;
        RandomListNode newHead = dummy;
        
        //copy node
        while (head != null){
            if (map.containsKey(head)){
                newNode = map.get(head);
            }else{
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            newHead.next = newNode;
            newHead = newNode;
            head = head.next;
        }
        //copy random
        head = temp;
        while (head != null){
           newNode = map.get(head);
           newNode.random = map.get(head.random);
           head = head.next;
        }
        return dummy.next;
    }
}
