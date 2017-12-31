Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].

Challenge 
O(nlogn) time

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    private class Pair{
        int sum;
        int index;
        public Pair(int sum, int index){
            this.sum = sum;
            this.index = index;
        }
    }
    public int[] subarraySumClosest(int[] nums) {
        if (nums == null || nums.length == 0){
            return nums;
        }
        int[] result = new int[2];
        if (nums.length == 1){
            result[0] = result[1] = 0;
            return result;
        }
        Pair[] preSumPair = new Pair[nums.length + 1];
        preSumPair[0] = new Pair(0, 0);
        int preSum = 0;
        for (int i = 1; i <= nums.length; i++){
            preSum = preSum + nums[i - 1];
            preSumPair[i] = new Pair(preSum, i);
        }
        Arrays.sort(preSumPair, new Comparator<Pair>(){
           public int compare(Pair a, Pair b){
               return a.sum - b.sum;
           } 
        });
        
        int diff = Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++){
            if (diff > preSumPair[i].sum - preSumPair[i - 1].sum){
                diff = preSumPair[i].sum - preSumPair[i - 1].sum;
                result[0] = Math.min(preSumPair[i].index, preSumPair[i - 1].index);
                result[1] = Math.max(preSumPair[i].index, preSumPair[i - 1].index) - 1;
            }
        }
        return result;
        
    }
}
