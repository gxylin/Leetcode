Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.

Write a function that takes an integer n and return all possible combinations of its factors.

Note:

    You may assume that n is always positive.
    Factors should be greater than 1 and less than n.

Examples:
input: 1
output:

[]

input: 37
output:

[]

input: 12
output:

[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

input: 32
output:

[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

Similar as combination sum
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        int[] nums = new int[n/2];
        for (int i = 0; i < nums.length; i++){
            nums[i] = i + 2;
        }
        dfs(result, new ArrayList<Integer>(), n, 0, nums);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> item, int target, int start, int[] nums){
        if (target == 1){
            if (item.size() > 1){
                result.add(new ArrayList<Integer>(item));
            }
            return;
        }
        for (int i = start; i < nums.length; i++){
            if (target % nums[i] == 0){
                item.add(nums[i]);
                dfs(result, item, target/nums[i], i, nums);
                item.remove(item.size() - 1);
            }  
        }
    }
}

Method 2:
public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    helper(result, new ArrayList<Integer>(), n, 2);
    return result;
}

public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
    if (n <= 1) {
        if (item.size() > 1) {
            result.add(new ArrayList<Integer>(item));
        }
        return;
    }
    
    for (int i = start; i <= n; ++i) {
        if (n % i == 0) {
            item.add(i);
            helper(result, item, n/i, i);
            item.remove(item.size()-1);
        }
    }
}
