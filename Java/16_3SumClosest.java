Given an array S of n integers, find three integers in S such that the sum is closest to a given number, 
target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
    
    
class Solution {
    public int threeSumClosest(int[] nums, int target) {
       Arrays.sort(nums);
        int best = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++){
            if (i > 0 && nums[i-1] == nums[i]){
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target){
                    return sum;
                }else if (sum < target){
                    left++;
                }else{
                    right--;
                }
                if (Math.abs(sum - target) < best){
                    best = Math.abs(sum- target);
                    result = sum;
                }
            }
        }
        return result;
    }
}


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 2; i < nums.length; i++){
            int left = 0; 
            int right = i - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target){
                    return sum;
                }else if (sum > target){
                    right--;
                }else{
                    left++;
                }
                
                if (Math.abs(sum - target) < min){
                    res = sum;
                    min = Math.abs(sum - target);
                }
            }
        }
        return res;
    }
}
