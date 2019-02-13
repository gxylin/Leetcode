Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of
an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

Method 1: TLE
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, int[] nums, int start){
        if (item.size() >= 2 && !res.contains(item)){
            res.add(new ArrayList<Integer>(item));
        }
        for (int i = start; i < nums.length; i++){
            if (item.isEmpty() || item.get(item.size()-1) <= nums[i]){
                item.add(nums[i]);
                dfs(res, item, nums, i+1);
                item.remove(item.size() - 1);
            }
        }
    }
}

Method 2:
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        dfs(res, new ArrayList<>(), nums, 0);
        List<List<Integer>> result = new ArrayList<>(res);
        return result;
    }
    private void dfs(Set<List<Integer>> res, List<Integer> item, int[] nums, int start){
        if (item.size() >= 2){
            res.add(new ArrayList<Integer>(item));
        }
        for (int i = start; i < nums.length; i++){
            if (item.isEmpty() || item.get(item.size()-1) <= nums[i]){
                item.add(nums[i]);
                dfs(res, item, nums, i+1);
                item.remove(item.size() - 1);
            }
        }
    }
}

Method 3: Best Solution note that this is not stardard backtracking
Pretty straightforward. Maybe one thing is: while nums is not necessarily sorted but we have to skip duplicates in each recursion, 
so we use a hash set to record what we have used in this particular recursion.
    
We don't do the same as standard backtrack to set.remove() because we have the set at each level, 
    not the same set used by all levels in the DFS.
    
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> item, int[] nums, int start){
        if (item.size() >= 2){
            res.add(new ArrayList<Integer>(item));
        }
        Set<Integer> set = new HashSet<>(); // note that this is not stardard backtracking
        for (int i = start; i < nums.length; i++){
            if (set.contains(nums[i])){
                continue;
            }
            if (item.isEmpty() || item.get(item.size()-1) <= nums[i]){
                set.add(nums[i]);
                item.add(nums[i]);
                dfs(res, item, nums, i+1);
                item.remove(item.size() - 1);
            }
        }
    }
}
