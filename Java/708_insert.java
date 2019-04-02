Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that 
it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the 
smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic 
list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single 
node. Otherwise, you should return the original given node.

The following example may help you understand the problem better:


https://www.cnblogs.com/grandyang/p/9981163.html

class Solution{
   class Node{
      int val;
      Node next;
      public Node(int val){
         this.val = val;
         next = null;
      }
   }
   Node insert(Node head, int insertVal){
      if (head == null){
         Node newHead = new Node(insertVal);
         newHead.next = newHead;
         return newHead;
      }
      Node prev = head;
      Node curr = head.next;
      while (curr != head){
         if (prev.val <= insertVal && insertVal <= next.val){
            break;
         }
         if (prev.val > next.val && (prev.val <= insertVal || insertVal <= next.val)){
             break;
         }
         prev = curr;
         curr = curr.next;
      }
      prev.next = new Node(insertVal);
      return head;
   }
}
