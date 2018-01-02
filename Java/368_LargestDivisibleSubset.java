Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of 
elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        int[] f = new int[nums.length]; // record longest chain at i
        int[] prev = new int[nums.length]; // record the index of previous one at i in the longest chain
        for (int i = 0; i < nums.length; i++){
            f[i] = 1;
            prev[i] = i;
        }
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] % nums[j] == 0){
                    if (f[i] < f[j] + 1){
                        f[i] = f[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }
        int max = 0;
        int max_i = 0;
        for (int i = 0; i < nums.length; i++){
            if (f[i] > max){
                max = f[i];
                max_i = i;
            }
        }
        while (prev[max_i] != max_i){
            result.add(0, nums[max_i]);
            max_i = prev[max_i];
        }
        result.add(0, nums[max_i]);
        return result;
    }
}
