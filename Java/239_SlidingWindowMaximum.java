Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?

Method 1:
Time complexity; O(n^2)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        int[] result = new int[len - k + 1];
        for (int i = 0; i < len-k+1; i++){
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++){
               max = Math.max(max, nums[j]); 
            }
            result[i] = max;
        }
        return result;
    }
}
Method 2: use deque O(n)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int len = nums.length;
        int[] result = new int[len - k + 1];
        LinkedList<Integer> doubleQ = new LinkedList<>(); //save index not number
        for (int i = 0; i < len; i++){
            if (!doubleQ.isEmpty() && doubleQ.peek() < i - k + 1){
                doubleQ.poll();
            }
            while (!doubleQ.isEmpty() && nums[i] >= nums[doubleQ.peekLast()]){//save the 2nd largest
                doubleQ.pollLast();
            }
            doubleQ.offer(i);
            if (i >= k - 1){
                result[i-k+1] = nums[doubleQ.peek()];
            }
        }
        return result;   
    }
    
}

Method 3: O(nlogk) use priority queue
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0){
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        Queue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
           public int compare (Integer i1, Integer i2){
               return i2 - i1;
           } 
        });
        for (int i = 0; i < k; i++){
            pq.offer(nums[i]);
        }
        res[0] = pq.peek();
        for (int i = k; i < n; i++){
            pq.remove(nums[i-k]); // take O(k)
            pq.offer(nums[i]);
            res[i-k+1] = pq.peek();
            
        }
        return res;
    }
}


Best solution:
https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array?page=3

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0){
            return new int[0];
        }
        int n = nums.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        maxLeft[0] = nums[0];
        maxRight[n-1] = nums[n-1];
        for (int i = 1; i < nums.length; i++){
            maxLeft[i] = i % k == 0 ? nums[i] : Math.max(maxLeft[i-1], nums[i]);
            int j = n - 1 - i;
            maxRight[j] = j % k == 0 ? nums[j] : Math.max(maxRight[j+1], nums[j]);
        }
        int[] res = new int[n-k+1];
        for (int i = 0; i < n-k+1; i++){
            res[i] = Math.max(maxRight[i], maxLeft[i+k-1]);
        }
        return res;
    }
}
