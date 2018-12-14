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
        return mergeSort(lists, 0, lists.length - 1);
    }
    private ListNode mergeSort(ListNode[] lists, int start, int end){
        if (start > end){
            return null;
        }
        if (start == end){
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeSort(lists, start, mid);
        ListNode right = mergeSort(lists, mid + 1, end);
        return merge(left, right);
    }
    private ListNode merge(ListNode l1, ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode node  = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 == null){
            node.next = l2;
        }
        if (l2 == null){
            node.next = l1;
        }
        return dummy.next;
    }
}

Method 2: heap priority queue
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare (ListNode n1, ListNode n2){
                return n1.val - n2.val;
            }
        });
        for (ListNode n : lists){
            if (n != null){
                pq.offer(n);
            }
        }
        while (!pq.isEmpty()){
            ListNode curr = pq.poll();
            node.next = curr;
            node = node.next;
            if (curr.next != null){
                pq.offer(curr.next);
            }
        }
        return dummy.next;
    }
}
