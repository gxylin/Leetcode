Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.


https://leetcode.com/articles/shortest-unsorted-continous-subarray/

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

Method 1: select sorting idea TLE 
Time complexity: O(n^2)
Space complexity: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int left = nums.length;
        int right = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[j] < nums[i]){
                    left = Math.min(left, i);
                    right = Math.max(right, j);
                }
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }
}

Method 2:
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] numsClone = nums.clone();
        Arrays.sort(nums);
        int left = nums.length - 1;
        int right = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != numsClone[i]){
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }
        return right - left <= 0 ? 0 : right - left + 1;
    }
}

Method 3: use stack to reduce time complexity and select sorting idea
Time complexity: O(n) two traverses
Space complexity: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length - 1;
        int right = 0;
        for (int i = 0; i < nums.length; i++){
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]){
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--){
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]){
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }
        return right - left <= 0 ? 0 : right - left + 1;
    }
}

Method 4: use one traverse to find min within the unsorted range and use another traverse to find the pivot index
do the same thing from right to left
Time complexity: O(n) four traverses
Space complexity: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] < nums[i-1]){
                flag = true;
            }
            if (flag){
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--){
            if (nums[i] > nums[i+1]){
                flag = true;
            }
            if (flag){
                max = Math.max(max, nums[i]);
            }
        }
        int left = 0;
        while (left < nums.length){
            if (nums[left] > min){
                break;
            }
            left++;
        }
        int right = nums.length - 1;
        while (right >= 0){
            if (nums[right] < max){
                break;
            }
            right--;
        }
        return right - left <= 0 ? 0 : right - left + 1;
    }
}


Method 5: Better solution
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int i = 0;
        int j = nums.length - 1;
        while (i < j && nums[i] == copy[i]){
            i++;
        }
        while (i < j && nums[j] == copy[j]){
            j--;
        }
        return i == j ? 0 : j - i + 1;
    }
}

Method 6: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right && nums[left+1] >= nums[left]){
            left++;
        }
        if (left == right){
            return 0;
        }
        while (left < right && nums[right-1] <= nums[right]){
            right--;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        while (left >= 0 && nums[left] > min){
            left--;
        }
        while (right < nums.length && nums[right] < max){
            right++;
        }
        return right - left - 1;
    }
}
