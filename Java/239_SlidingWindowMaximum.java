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
Method 2: use deque
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
