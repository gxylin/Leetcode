Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
Example:

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
Note:

All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.


class MyLinkedList {
    class SingleNode {
        int val;
        SingleNode next;
        public SingleNode (int val){
            this.val = val;
        }
    }
    SingleNode head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        int count = 0;
        SingleNode node = head;
        while (node != null && count < index){
            node = node.next;
            count++;
        }
        if (count == index && node != null){
            return node.val;
        }
        return -1;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        SingleNode node = new SingleNode(val);
        node.next = head; //note how to add head note//
        head = node;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        SingleNode node = head;
        while (node != null && node.next != null){
            node = node.next;
        }
        node.next = new SingleNode(val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        int count = 0;
        SingleNode node = head;
        while (node != null && count < index - 1){
            node = node.next;
            count++;
        }
        if (index == 0){//special case
            SingleNode newNode = new SingleNode(val);
            newNode.next = head;
            head = newNode;
        }else if (count == index - 1 && node != null){
            SingleNode newNode = new SingleNode(val);
            newNode.next = node.next;
            node.next = newNode;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        int count = 0;
        SingleNode node = head;
        while (node != null && count < index - 1){
            node = node.next;
            count++;
        }
        if (count == index -1 && node != null && node.next != null){
            node.next = node.next.next;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
