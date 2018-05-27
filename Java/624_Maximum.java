 Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:

Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Note:

    Each given array will have at least 1 number. There will be at least two non-empty arrays.
    The total number of the integers in all the m arrays will be in the range of [2, 10000].
    The integers in the m arrays will be in the range of [-10000, 10000].


Method 1:
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minInd = 0;
        int maxInd = 0;
        int minSec = Integer.MAX_VALUE;
        int maxSec = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.size(); i++){
            int small = arrays.get(i).get(0);
            int large = arrays.get(i).get(arrays.get(i).size() - 1);
            if (small < min){
                minSec = min;
                min = small;
                minInd = i;
            }else if (small < minSec){
                minSec = small;
            }
            if (large > max){
                maxSec = max;
                max = large;
                maxInd = i;
            }else if (max > maxSec){
                maxSec = large;
            }
        }
        return minInd != maxInd ? max - min : Math.max(maxSec - min, max - minSec);
    }
}

Method 2:
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); i++){
            int small = arrays.get(i).get(0);
            int large = arrays.get(i).get(arrays.get(i).size() - 1);
            ans = Math.max(ans, Math.max(Math.abs(max - small), Math.abs(large - min)));
            min = Math.min(min, small);
            max = Math.max(max, large);
        }
        return ans;
    }
}
