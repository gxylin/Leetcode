Given an array of n integer with duplicate number, and a moving window(size k),
move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.

Have you met this question in a real interview? 
Example
For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]

At first the window is at the start of the array like this

[|1, 2, 7| ,7, 8] , return the maximum 7;

then the window move one step forward.

[1, |2, 7 ,7|, 8], return the maximum 7;

then the window move one step forward again.

[1, 2, |7, 7, 8|], return the maximum 8;

Challenge 
o(n) time and O(k) memory

https://www.jiuzhang.com/solution/sliding-window-maximum/

public class Solution {
    /*
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The maximum number inside the window at each moving
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        for (int i = 0; i < k - 1; i++){
            inDeque(deque, i, nums);
        }
        for (int i = k - 1; i < nums.length; i++){
            inDeque(deque, i, nums);
            result.add(nums[deque.peekFirst()]);
            outDeque(deque, i-k+1, nums);
        }
        return result;
    }
    private void inDeque(Deque<Integer> deque, int i, int[] nums){
        while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]){
            deque.pollLast();
        }
        deque.offerLast(i);
    }
    private void outDeque(Deque<Integer> deque, int i, int[] nums){
        if (nums[i] == nums[deque.peekFirst()]){
            deque.pollFirst();
        }
    }
};

Method 2:
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

