Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6

class Solution {
    public int findShortestSubArray(int[] nums) {
        int ans = 0;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], new int[]{1, i, i});;
            }else{
                int[] temp = map.get(nums[i]);
                temp[0]++;
                temp[2] = i;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int[] val : map.values()){
            int freq = val[0];
            int start = val[1];
            int end = val[2];
            if (freq > max){
                max = freq;
                ans = end - start + 1;
            }else if (freq == max){
                ans = Math.min(ans, end - start + 1);
            }
        }
        return ans;
    }
}

Method 2: One pass, O(N), best solution
class Solution {
    public int findShortestSubArray(int[] nums) {
        int res = 0;
        int degree = 0;
        Map<Integer, Integer> map = new HashMap<>();//key: num, val: current degree
        Map<Integer, Integer> first = new HashMap<>();//key: num val: first occurance
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            first.putIfAbsent(nums[i], i);
            if (degree < map.get(nums[i])){
                degree = map.get(nums[i]);
                res = i - first.get(nums[i]) + 1;
            }else if (degree == map.get(nums[i])){
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
        }
        return res;
    }
}
