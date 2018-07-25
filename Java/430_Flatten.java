You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL


Method 1:

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        if (head == null){
            return head;
        }
        dfs(head);//return tail;
        return head;
    }
    private Node dfs(Node head){
        Node node = head;
        Node tail = head;
        while  (node != null){
            while (node.next != null && node.child == null){
                node = node.next;
            }
            if (node.next == null && node.child == null){
                tail = node;
                break;
            }
            //node.child != null && (node.next == null || node.next != null)
            Node temp = node.next;
            node.next = node.child;
            node.child.prev = node;
            Node childTail = dfs(node.child);//return the last of the child
            childTail.next = temp;
            node.child = null;
            if (temp != null){
                temp.prev = childTail;
            }else{
                return childTail;
            }
            node = temp;
        }
        return tail;
    }
}

Method 2:
https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/discuss/150321/Easy-Understanding-Java-beat-95.7-with-Explanation

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        if (head == null){
            return head;
        }
        Node curr = head;
        while (curr != null){
            if (curr.child == null){
                curr = curr.next;
                continue;
            }
            Node temp = curr.child;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = curr.next;
            curr.next = curr.child;
            curr.child.prev = curr;
            curr.child = null;
            if (temp.next != null){
                temp.next.prev = temp;
            }
        }
        return head;
    }
}
