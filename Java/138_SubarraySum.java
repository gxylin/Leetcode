Given an integer array, find a subarray where the sum of numbers is zero. 
Your code should return the index of the first number and the index of the last number.

 Notice
There is at least one subarray that it's sum equals to zero.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            
            if (map.containsKey(sum)){
                result.add(map.get(sum) + 1);
                result.add(i);
                return result;
            }
            map.put(sum, i);
        }
        return result;
    }
}

Method 2: use prefix Sum
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] pre = new int[nums.length+1];
        pre[0] = 0;
        for (int i = 1; i <= nums.length; i++){
            pre[i] = pre[i-1] + nums[i-1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pre.length; i++){
            if (map.containsKey(pre[i])){
                result.add(map.get(pre[i]));
                result.add(i-1);
                return result;
            }
            map.put(pre[i], i);
        }
        return result;
    }
}
