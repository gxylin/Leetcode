Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4


class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private Map<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        Node cur = map.get(key);
        cur.next.prev = cur.prev;
        cur.prev.next = cur.next;
        cur.prev = null;
        cur.next = null;
        
        moveToTail(cur);
        return map.get(key).val;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)){
            Node insert = new Node(key, value);
            map.put(key, insert);
            if (capacity > 0){
                capacity--;
            }else{
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.prev = head;
            }
            moveToTail(insert);
        }else{
            int v = get(key);
            map.get(key).val = value;
        }
    }
    
    private void moveToTail(Node cur){        
        cur.next = tail;
        tail.prev.next = cur;
        cur.prev = tail.prev;
        tail.prev = cur;        
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



Code that was written in the 2nd time
class LRUCache {
    class LinkedNode {
        int val;
        int key;
        LinkedNode prev;
        LinkedNode next;
        public LinkedNode(int key, int val){
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }
    Map<Integer, LinkedNode> map;
    LinkedNode head;
    LinkedNode tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new LinkedNode(-1, -1);
        tail = new LinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)){
            LinkedNode node = map.get(key);
            prepareToMove(node);
            moveToTail(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            LinkedNode node = map.get(key);
            node.val = value;
            prepareToMove(node);
            moveToTail(node);
        }else{
            if (map.size() == capacity){
                LinkedNode after = head.next;
                after.next.prev = head;
                head.next = after.next;
                after.prev = null;
                after.next = null;
                map.remove(after.key);  
            }
            LinkedNode node = new LinkedNode(key, value);
            moveToTail(node);
            map.put(key, node);
        }
    }
    private void moveToTail(LinkedNode node){
        LinkedNode before = tail.prev;
        before.next = node;
        node.prev = before;
        node.next = tail;
        tail.prev = node;
    }
    private void prepareToMove(LinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
