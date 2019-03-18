Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to 
nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

Similar as House Robber I
dp[i] denotes the max pointers when reach up to number i, either delete it to get dp[i-2] + i * count[i] or keep it to get dp[i-1]

Method 1: best solution
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int max = 0;
        for (int i : nums){
            max = Math.max(max, i);
        }
        int[] count = new int[max+1];
        for (int i : nums){
            count[i]++;
        }
        int[] dp = new int[max+1];
        dp[0] = 0;
        dp[1] = 1 * count[1];
        for (int i = 2; i <= max; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + i * count[i]);
        }
        return dp[max];
    }
}


Better solution:

Method 2: DP + treemap

Time complexity: O(nlogn)
Space complexity: O(n)
 class Solution {
    public int deleteAndEarn(int[] nums) {
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        for (int i : nums){
            treemap.put(i, treemap.getOrDefault(i, 0) + 1);
        }
        int n = treemap.size();
        int[] dp = new int[n+1];
        int i = 1;
        for (int key : treemap.keySet()){
            Integer preKey = treemap.lowerKey(key);
            if (preKey == null){
                dp[i] = key * treemap.get(key);
            }else{
                if (preKey == key - 1){
                    dp[i] = Math.max(dp[i-1], dp[i-2] + key * treemap.get(key));
                }else{
                    dp[i] = dp[i-1] + key * treemap.get(key);
                }
            }
            i++;
        }
        return dp[n];
    }
}


class Solution {
    public int deleteAndEarn(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else{
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        int n = map.size();
        int[] dp = new int[n + 1];
        int i = 2;
        dp[0] = 0;
        for (int key : map.keySet()){
            if (key == map.firstKey()){
                dp[1] = map.firstKey() * map.get(map.firstKey());
            }
            Integer lowerKey = map.lowerKey(key);
            if (lowerKey == key - 1){
                dp[i] = Math.max(dp[i-2] + map.get(key) * key, dp[i-1]);
            }else{
                dp[i] = dp[i-1] + map.get(key) * key;
            }
            i++;
        }
        return dp[n];
    }
}

