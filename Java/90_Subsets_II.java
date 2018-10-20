Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(result, new ArrayList<>(), nums, 0, visited);
        return result;
    }
    private void dfs(List<List<Integer>> result , List<Integer> item, int[] nums, int start, boolean[] visited){
        result.add(new ArrayList<>(item));
        
        for (int i = start; i < nums.length; i++){
            if (i != 0 && nums[i-1] == nums[i] && !visited[i-1]){
                continue;
            }
            item.add(nums[i]);
            visited[i] = true;
            dfs(result, item, nums, i+1, visited);
            item.remove(item.size() - 1);
            visited[i] = false;
        }
    }
}


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, int[] nums, int start){
        res.add(new ArrayList<Integer>(item));
        for (int i = start; i < nums.length; i++){
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            item.add(nums[i]);
            dfs(res, item, nums, i + 1);
            item.remove(item.size() - 1);
        }
    }
}
