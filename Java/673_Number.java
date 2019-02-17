Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] lengths = new int[nums.length]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[nums.length]; //count[i] = number of longest ending in nums[i]
        for (int i = 0; i < nums.length; i++){
            lengths[i] = 1;
        }
        Arrays.fill(counts, 1);
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    if (lengths[i] < lengths[j] + 1){
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    }else if (lengths[i] == lengths[j] + 1){
                        counts[i] += counts[j];
                    }
                }
            }
        }
        int longest = 0;
        int count = 0;
        for (int length : lengths){
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < nums.length; i++){
            if (lengths[i] == longest){
                count += counts[i];
            }
        }
        return count;
    }
}

Best solution:
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        int longest = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            lengths[i] = 1;
            counts[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    int cand = lengths[j] + 1;
                    if (lengths[i] < cand){
                        lengths[i] = cand;
                        counts[i] = counts[j];
                    }else if (lengths[i] == cand){
                        counts[i] += counts[j];
                    }
                }
            }
            if (lengths[i] == longest){
                count += counts[i];
            }else if (lengths[i] > longest){
                longest = lengths[i];
                count = counts[i];
            }
        }
        return count;
    }
}

class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            lengths[i] = 1;
        }
        Arrays.fill(counts, 1);
        int longest = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    if (lengths[i] < lengths[j] + 1){
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    }else if (lengths[i] == lengths[j] + 1){
                        counts[i] += counts[j];
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; i++){
             if (lengths[i] == longest){
                count += counts[i];
            }else if (lengths[i] > longest){
                longest = lengths[i];
                count = counts[i];
            }
        }
        return count;
    }
}

