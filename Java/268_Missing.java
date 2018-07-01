Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums){
            set.add(i);
        }
        int n = nums.length + 1;
        for (int i = 0; i < n; i++){
            if (!set.contains(i)){
                return i;
            }
        }
        return -1;
    }
}

Method 2:
Time complexity: O(nlogn)
Space complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if (nums[nums.length-1] != nums.length){
            return nums.length;
        }else if (nums[0] != 0){
            return 0;
        }
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != i){
                return i;
            }
        }
        return -1;
    }
}

Method 3:
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++){
            ans ^= i;
            ans ^= nums[i];
        }
        return ans;
    }
}
Because we know that nums contains nnn numbers and that it is missing exactly one number on the range [0..n−1][0..n-1][0..n−1], we know that nnn definitely replaces the missing number in nums. Therefore, if we initialize an integer to nnn and XOR it with every index and value, we will be left with the missing number. Consider the following example (the values have been sorted for intuitive convenience, but need not be):

Index 	0 	1 	2 	3
Value 	0 	1 	3 	4

x ^ x = 0;
x ^ 0 = x;

Method 4: Gauss' Formula, sum from 1 to n = (n+1) * n / 2;
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = (n+1) * n / 2;
        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        return expectedSum - sum;
    }
}
