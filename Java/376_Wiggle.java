A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive 
and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is 
trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast,
[1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because 
its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by
deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original 
order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?

https://leetcode.com/articles/wiggle-subsequence/
up[i] refers to the length of the longest wiggle subsequence obtained so far considering i^{th}i
the element as the last element of the wiggle subsequence and ending with a rising wiggle.

Similarly, down[i]down[i] refers to the length of the longest wiggle subsequence obtained so far considering i^{th}i
the element as the last element of the wiggle subsequence and ending with a falling wiggle.


Method 1: 
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2){
            return nums.length;
        }
        int n = nums.length;
        int[] up = new int[n]; //dp[i] denote longest result ending at i, (note result must include the element at i)
        int[] down = new int[n];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    up[i] = Math.max(up[i], down[j] + 1);
                }else if (nums[i] < nums[j]){
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }
}

Method 2:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2){
            return nums.length;
        }
        int n = nums.length;
        int[] up = new int[n]; //dp[i] denote longest result ending at i, (note result may or may include the element at i)
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++){
            if (nums[i] > nums[i-1]){
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            }else if (nums[i] < nums[i-1]){
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            }else{
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[n-1], down[n-1]);
    }
}

Method 3: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2){
            return nums.length;
        }
        int n = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++){
            if (nums[i] > nums[i-1]){
                up = down + 1;
            }else if (nums[i] < nums[i-1]){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
