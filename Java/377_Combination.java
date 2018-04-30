Given an integer array with all positive numbers and no duplicates, find the number of possible combinations 
that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Method 1: DFS TLE
class Solution {
    public int combinationSum4(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(result, new ArrayList<>(), nums, target);
        return result.size();
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] nums, int target){
        if (target == 0){
            result.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (target < nums[i]){
                break;
            }
            item.add(nums[i]);
            dfs(result, item, nums, target - nums[i]);
            item.remove(item.size() - 1);
        }
    }
}

Method 2: recursion + memorization
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int combinationSum4(int[] nums, int target) {
        if (map.containsKey(target)){
            return map.get(target);
        }
        if (target == 0){
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if (target >= nums[i]){
                ans += combinationSum4(nums, target - nums[i]);
            }
        }
        map.put(target, ans);
        return ans;
    }
}
