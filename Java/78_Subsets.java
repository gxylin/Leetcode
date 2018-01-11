Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] nums, int start){
        result.add(new ArrayList<Integer>(item));
        for (int i = start; i < nums.length; i++){
            item.add(nums[i]);
            dfs(result, item, nums, i + 1);
            item.remove(item.size() - 1);
        }
    }
}
