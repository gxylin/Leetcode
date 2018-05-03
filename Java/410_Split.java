Given an array which consists of non-negative integers and an integer m, you can split the array into
m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation:-8ms-Binary-Search-Java?page=2

class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        for (int num : nums){
            max = Math.max(max, num);
            sum += num;
        }
        int start = max;
        int end = sum;
        while (start <= end){
            int mid = start + (end - start) / 2;
            if (valid(mid, nums, m)){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }
    private boolean valid(int target, int[] nums, int m){
        int count = 1;
        int sum = 0;
        for (int num : nums){
            sum += num;
            if (sum > target){
                count++;
                sum = num;
                if (count > m){
                    return false;
                }
            }
        }
        return true;
    }
}
