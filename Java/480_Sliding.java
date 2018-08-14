Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

The similar as 295. Find Median from Data Stream
Use two PQ
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        Queue<Integer> minPQ = new PriorityQueue<>();//store the larger half, if even number, put into minPQ
        Queue<Integer> maxPQ = new PriorityQueue<>(new Comparator<Integer>(){//store the smaller half
            public int compare (Integer i1, Integer i2){
                return i2.compareTo(i1);
            }
        });
        int n = nums.length;
        if (n == 0){
            return new double[0];
        }
        double[] res = new double[n-k+1];
        for (int i = 0; i < n; i++){
            add(minPQ, maxPQ, nums[i]);
            if (i >= k - 1){
                res[i-k+1] = getMedian(minPQ, maxPQ);
                remove(minPQ, maxPQ, nums[i-k+1]);
            }
        }
        return res;
    }
    private void add(Queue<Integer> minPQ, Queue<Integer> maxPQ, int num){
        if (num < getMedian(minPQ, maxPQ)){
            maxPQ.offer(num);
        }else{
            minPQ.offer(num);
        }
        if (maxPQ.size() > minPQ.size()){
            minPQ.offer(maxPQ.poll());
        }else if (minPQ.size() - maxPQ.size() > 1){
            maxPQ.offer(minPQ.poll());
        }
        
    }
    private void remove (Queue<Integer> minPQ, Queue<Integer> maxPQ, int num){
        if (num < getMedian(minPQ, maxPQ)){
            maxPQ.remove(num);
        }else{
            minPQ.remove(num);
        }
        if (maxPQ.size() > minPQ.size()){
            minPQ.offer(maxPQ.poll());
        }else if (minPQ.size() - maxPQ.size() > 1){
            maxPQ.offer(minPQ.poll());
        }
    }
    private double getMedian(Queue<Integer> minPQ, Queue<Integer> maxPQ){
        if (minPQ.size() == 0 && maxPQ.size() == 0){
            return 0.0;
        }
        if (minPQ.size() == maxPQ.size()){
            return ((double)minPQ.peek() + (double)maxPQ.peek()) / 2.0; 
        }else{
            return (double) minPQ.peek();
        }
    }
}
