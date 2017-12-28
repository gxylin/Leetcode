Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null){
            return result;
        }
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++){
            if (i != 0 && nums[i-1] == nums[i]){
                continue;
            }
            int left = i + 1; 
            int right = nums.length - 1;
            int target = - nums[i];
            while (left < right){
                int sum = nums[left] + nums[right];
                if (sum == target){
                    List<Integer> item = new ArrayList<>();
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
        return result;
    }
}
