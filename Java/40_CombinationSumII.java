Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return result;
        }
        Arrays.sort(candidates);
        dfs(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int[] candidates, int target, int start){
        if (target == 0){
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target){
                break;
            }
            if (i != start && candidates[i] == candidates[i-1]){
                continue;
            }
            item.add(candidates[i]);
            dfs(result, item, candidates, target - candidates[i], i + 1);
            item.remove(item.size() - 1);
        }
    }
}
