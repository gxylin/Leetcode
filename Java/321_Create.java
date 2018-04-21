Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of
length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. 
Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]

https://leetcode.com/problems/create-maximum-number/discuss/77285/Share-my-greedy-solution
Time complexity: O((m+n)^2)
class Solution {
    //step 1: find the max Array within one array
    //step 2: merge two max arrays into one array
    //step 3: find the max one with different combination of merge
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; i++){
            int[] maxArray1 = maxArray(nums1, i);
            int[] maxArray2 = maxArray(nums2, k-i);
            int[] candidate = merge(maxArray1, maxArray2, k);
            if (greater(candidate, 0, ans, 0)){
                ans = candidate;
            }
        }
        return ans;
    }
    private int[] maxArray(int[] nums, int len){
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++){
            while (stack.size() + nums.length - i > len && !stack.isEmpty() && stack.peek() < nums[i]){
                stack.pop();
            }
            if (stack.size() < len){
                stack.push(nums[i]);
            }
        }
        for (int i = len - 1; i >= 0; i--){
            ans[i] = stack.pop();
        }
        return ans;
    }
    private int[] merge(int[] nums1, int[] nums2, int k){
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++){
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++]; 
        }
        return ans;
    }
    private boolean greater(int[] nums1, int i, int[] nums2, int j){
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]){
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}
