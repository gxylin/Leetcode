We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 

Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with 
difficulty at most worker[i]. 

Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, 
his profit is $0.

What is the most profit we can make?

Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100 
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
Notes:

1 <= difficulty.length = profit.length <= 10000
1 <= worker.length <= 10000
difficulty[i], profit[i], worker[i]  are in range [1, 10^5]

Method 1: two points
Time complexity:O(N*M), N-- number of worker, M -- number of difficulty
Space complexity: O(1)
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int ans = 0;
        for (int i = 0; i < worker.length; i++){
            int max = 0;
            for (int j = 0; j < difficulty.length; j++){
                if (difficulty[j] <= worker[i]){
                    max = Math.max(max, profit[j]);
                }
            }
            ans += max;
        }
        return ans;
    }
}

Method 2: Best solution TreeMap
Time complexity: O(mlogm + nlogm)
Space complexity: O(m)
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = worker.length;
        int m = profit.length;
        int res = 0;
        TreeMap<Integer, Integer> treemap = new TreeMap<>();//key: diff, value : the max profit at this key
        for (int i = 0; i < m; i++){
            treemap.put(difficulty[i], Math.max(profit[i], treemap.getOrDefault(difficulty[i], 0))); // in case the same diff but different profit
        }
        int max = 0;
        // here is the trick about how to get the max value below or equal the key
        for (int diff : treemap.keySet()){
            max = Math.max(max, treemap.get(diff));
            treemap.put(diff, max); // change treemap to key: diff, value: the max profit below or equal this key
        }
        /////////////
        
        for (int i = 0; i < n; i++){
            Integer key = treemap.floorKey(worker[i]);
            if (key != null){
                res += treemap.get(key);
            }
        }
        return res;
    }
}
