Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Method 1: divide & conquer
Time complexity: Nlogk
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        return helper(lists, 0 , lists.length - 1);
    }
    private ListNode helper(ListNode[] lists, int start, int end){
        if (start >= end){
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid + 1, end);
        return merge(left, right);
    }
    private ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                lastNode.next = l2;
                l2 = l2.next;
            }else{
                lastNode.next = l1;
                l1 = l1.next;
            }
            lastNode = lastNode.next;
        }
        if (l1 != null){
            lastNode.next = l1;
        }
        if (l2 != null){
            lastNode.next = l2;
        }
        return dummy.next;
    }
    
}

Method 2: heap priority queue
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        Queue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }
        });

        for (int i = 0; i < lists.length; i++){
            if (lists[i] != null){
                pq.offer(lists[i]);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (!pq.isEmpty()){
            ListNode temp = pq.poll();
            lastNode.next = temp;
            lastNode = temp;
            if (temp.next != null){
               pq.offer(temp.next); 
            }
        }
        return dummy.next;
    }
}
