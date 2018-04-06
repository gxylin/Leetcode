Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

Method 1: 
Time complexity: O(n^3)
Space complexity: O(1)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length -3 ; i++){
            if (i > 0 && nums[i-1] == nums[i]){
                continue;
            }
            threeSum(result, nums, target - nums[i], i);
        }
        return result;
    }
    private void threeSum(List<List<Integer>> result, int[] nums, int target, int start){
        for (int i = start + 1; i < nums.length - 2; i++){
            if (i > start + 1 && nums[i-1] == nums[i]){
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target){
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[start]);
                    item.add(nums[i]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    result.add(item);
                    left++;
                    right--;
                    while (left < right && nums[left-1] == nums[left]){
                        left++;
                    }
                    while (left < right && nums[right+1] == nums[right]){
                        right--;
                    }
                }else if (sum < target){
                    left++;
                }else{
                    right--;
                }
            }
        }
    }
}
