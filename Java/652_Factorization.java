A non-negative numbers can be regarded as product of its factors.
Write a function that takes an integer n and return all possible combinations of its factors.

 Notice

Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combination.
Have you met this question in a real interview? Yes
Example
Given n = 8
return [[2,2,2],[2,4]]
// 8 = 2 x 2 x 2 = 2 x 4.

Given n = 1
return []

Given n = 12
return [[2,6],[2,2,3],[3,4]]

Method 1:
public class Solution {
    /*
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 2, n);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int lastFactor, int remain){
        if (remain == 1){
            if (item.size() != 1)
                result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = lastFactor; i <= remain/i; i++){
            if (remain % i == 0){
                item.add(i);
                dfs(result, item, i, remain/i);
                item.remove(item.size() - 1);
            }
        }
        item.add(remain);
        dfs(result,item, remain, 1);
        item.remove(item.size() - 1);
    }
}

Method 2:
public class Solution {
    /*
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 2, n);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int lastFactor, int remain){
        if (!item.isEmpty()){
            item.add(remain);
            result.add(new ArrayList<Integer>(item));
            item.remove(item.size() - 1);
        }
        for (int i = lastFactor; i <= remain/i; i++){
            if (remain % i == 0){
                item.add(i);
                dfs(result, item, i, remain/i);
                item.remove(item.size() - 1);
            }
        }
    }
}
