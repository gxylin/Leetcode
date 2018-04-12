Find all possible combinations of k numbers that add up to a number n, given that only numbers 
from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++){
            nums[i] = i+1;
        }
        dfs(result, new ArrayList<Integer>(), nums, k, n, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] nums, int k, int target, int start){
        if (target == 0 && k == 0){
            result.add(new ArrayList<>(item));
            return;
        }
        for (int i = start; i < nums.length; i++){
            if (nums[i] > target){
                break;
            }
            item.add(nums[i]);
            dfs(result, item, nums, k - 1, target - nums[i], i+1);
            item.remove(item.size() - 1);
        }
    }
}
