Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), n, k, 1);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int n, int k, int start){
        if (k == 0){
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = start; i <= n; i++){
            if (item.contains(i)){
                continue;
            }
            item.add(i);
            dfs(result, item, n, k-1, i+1);
            item.remove(item.size() - 1);
        }
    }
}
