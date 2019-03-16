Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        boolean[] marked = new boolean[nums.length];
        dfs(result, new ArrayList<Integer>(), nums, marked);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] nums, boolean[] marked){
        if (item.size() == nums.length){
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (i != 0 && nums[i] == nums[i-1] && !marked[i-1]){
                continue;
            }
            if (!marked[i]){
                item.add(nums[i]);
                marked[i] = true;
                dfs(result, item, nums, marked);
                item.remove(item.size() - 1);
                marked[i] = false;
            }
        }
    }
}

Better version
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res  = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(res, new ArrayList<Integer>(), visited, nums);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, boolean[] visited, int[] nums){
        if (item.size() == nums.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
            visited[i] = true;
            item.add(nums[i]);
            dfs(res, item, visited, nums);
            item.remove(item.size() - 1);
            visited[i] = false;
        }
    }
}


Check the similar question: 996. Number of Squareful Arrays
https://github.com/optimisea/Leetcode/blob/master/Java/996_Number.java
