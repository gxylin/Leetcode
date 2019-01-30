By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Method 1:
https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i <= n; i++){
            fact *= i;
            list.add(i);
        }
        k--;
        for (int i = 0; i < n; i++){
            fact /= (n-i);
            int index = k / fact;
            sb.append(list.remove(index));
            k -= index * fact;
        }
        return sb.toString();
    }
}

class Solution {
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++){
            nums[i-1] = i;
        }
        List<List<Integer>> list = permute(nums);
        StringBuilder sb = new StringBuilder();
        for (int i : list.get(k-1)){
            sb.append(i);
        }
        return sb.toString();
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        dfs(res, new ArrayList<Integer>(), seen, nums);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, Set<Integer> seen, int[] nums){
        if(item.size() == nums.length){
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (seen.contains(nums[i])){
                continue;
            }
            item.add(nums[i]);
            seen.add(nums[i]);
            dfs(res, item, seen, nums);
            seen.remove(nums[i]);
            item.remove(item.size() - 1);
        }
    }
}

Method 2: DFS TLS
class Solution {
    public String getPermutation(int n, int k) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(result, list, n);
        StringBuilder sb = new StringBuilder();
        for (String str : result.get(k-1)){
            sb.append(str);
        }
        return sb.toString();
    }
    private void dfs(List<List<String>> result, List<String> list, int n){
        if (list.size() == n){
            result.add(new ArrayList<String>(list));
            return;
        }
        for (int i = 1; i <= n; i++){
            String str = String.valueOf(i);
            if (list.contains(str)){
                continue;
            }
            list.add(str);
            dfs(result, list, n);
            list.remove(list.size() - 1);
        }
    }
}
