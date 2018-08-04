Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0 
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.


Method 1: Brute Force
Time complexity: O(n^2*logk)
Space complexity: O(k)
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> maxPQ = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare (Integer i1, Integer i2){
                return i2 - i1;
            }
        });
        for (int i = 0; i < n ; i++){
            for (int j = i + 1; j < nums.length; j++){
                int cand = Math.abs(nums[i] - nums[j]);
                if (maxPQ.size() < k){
                    maxPQ.offer(cand);
                }else if (maxPQ.size() == k && maxPQ.peek() > cand){
                    maxPQ.poll();
                    maxPQ.offer(cand);
                }        
            }
        }
        return maxPQ.peek();
    }
}

Method 2:
way to define the K-th smallest pair distance: given an integer num, let count(num) denote the number of pair 
distances that are no greater than num, then the K-th smallest pair distance will be the smallest integer such that count(num) >= K.
    
If we sort the input array in ascending order, this problem can actually be rephrased as finding the kth smallest element in a sorted 
matrix, where the matrix element at position (i, j) is given by matrix[i][j] = nums[j] - nums[i]

https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm

Time complexity: O(nlogn + nlogd) d is the max - min
Space complexity: O(1)
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = nums[n-1] - nums[0];
        while (low <= high){
            int mid = low + (high - low) / 2;
            int count = getLessEqual(nums, mid);
            if (count <= k - 1){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
    private int getLessEqual(int[] nums, int val){
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            int j = i + 1;
            while (j < nums.length && nums[j] - nums[i] <= val){
                j++;
            }
            res += j - i - 1;
        }
        return res;
    }
}
