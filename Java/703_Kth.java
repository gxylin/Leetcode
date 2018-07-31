Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.

Method 1: Two PQ
class KthLargest {
    Queue<Integer> minPQ;
    Queue<Integer> maxPQ;
    int K;
    public KthLargest(int k, int[] nums) {
        minPQ = new PriorityQueue<Integer>();
        maxPQ = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare (Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        K = k;
        int  N = nums.length;
        for (int num : nums){
            minPQ.offer(num);
        }
        for (int i = 0; i < N - k + 1; i++){
            maxPQ.offer(minPQ.poll());
        }
    }
    
    public int add(int val) {
        if (minPQ.isEmpty()){
            minPQ.offer(val);
            if (minPQ.size() > K - 1){
                maxPQ.offer(minPQ.poll());
            }
        }else if (val <= minPQ.peek()){
            maxPQ.offer(val);
        }else{
            maxPQ.offer(minPQ.poll());
            minPQ.offer(val);
        }
        return maxPQ.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
 
 
 Method 2: One PQ
 class KthLargest {
    Queue<Integer> minPQ;
    int K;
    public KthLargest(int k, int[] nums) {
        minPQ = new PriorityQueue<Integer>();
        K = k;
        for (int num : nums){
            if (minPQ.size() < k || num > minPQ.peek()){
                minPQ.offer(num);
            }
            if (minPQ.size() > k){
                minPQ.poll();
            }
        }
    }
    
    public int add(int val) {
        if (minPQ.size() < K || val > minPQ.peek()){
            minPQ.offer(val);
        }
        if (minPQ.size() > K){
            minPQ.poll();
        }
        return minPQ.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
