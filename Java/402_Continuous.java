Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number. 
(If their are duplicate answer, return anyone)

Have you met this question in a real interview? 
Example
Give [-3, 1, 3, -3, 4], return [1,4].


Similar to https://github.com/optimisea/Leetcode/blob/master/Java/53_MaximumSubarray.java

local denotes the maximum value of continuous subarray that ends at first i element

public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] A) {
        List<Integer> result = new ArrayList<>();
        result.add(0, 0);
        result.add(1, 0);
        int local = 0;
        int global = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for (int i = 0; i < A.length; i++){
            if (local < 0){
                local = A[i];
                left = i;
                right = i;
            }else{
                local += A[i];
                right = i;
            }
            if (local >= global){
                global = local;
                result.set(0, left);
                result.set(1, right);
            }
        }
        return result;
    }
}
