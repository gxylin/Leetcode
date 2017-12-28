Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        dfs(result, new ArrayList<Integer>(), nums);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] nums){
        if (item.size() == nums.length){
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length ; i++){
            if (item.contains(nums[i])){
                continue;
            }
            item.add(nums[i]);
            dfs(result, item, nums);
            item.remove(item.size() - 1);
        }
    }
}
