Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0){
            return result;
        }
        Arrays.sort(candidates);
        dfs(result, new ArrayList<Integer>(), target, candidates, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int target, int[] candidates, int start){
        if (target == 0){
            result.add(new ArrayList<Integer>(item));
            return;
        }
       
        for (int i = start; i < candidates.length; i++){
             if (candidates[i] > target){
                 break;
             }
            item.add(candidates[i]);
            dfs(result, item, target - candidates[i], candidates, i);
            item.remove(item.size() - 1);
        }
    }
}
