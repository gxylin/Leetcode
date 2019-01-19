Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.


    
   Check Leetcode 918 for the case of circular array
    
    
Method 1: Greey
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums){
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0){
                sum = 0;
            }
        }
        return max;
    }
}

Better to handle corner case 
class Solution {
    public int maxSubArray(int[] nums) {
        int global = Integer.MIN_VALUE;
        int local = 0;
        for (int i = 0; i< nums.length; i++){
            local = Math.max(nums[i], local + nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}

Method 2: DP
f[i] denotes the maxium value of the continuous subarray that ends at the first i elements

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++){
            f[i] = Math.max(nums[i-1], nums[i-1] + f[i-1]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++){
            max = Math.max(max, f[i]);
        }
        return max;
    }
}

Method 3: rolling array
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[2];
        f[0] = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++){
            f[i%2] = Math.max(nums[i-1], nums[i-1] + f[(i-1)%2]);
            max = Math.max(max, f[i%2]);
        }
        return max;
    }
}

Method 4: DP
Space complexity: O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int global = nums[0];
        int local = nums[0];
        for (int i = 1; i< nums.length; i++){
            local = Math.max(nums[i], local + nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}



If the required output is changed to the desired array not just sum
class Solution {
    public int[] maxSubArray(int[] nums) {
        int global = Integer.MIN_VALUE;
        int local = 0;
        int start = 0;
        int end = 0;
        int[] index = new int[2];
        for (int i = 0; i< nums.length; i++){
            if (nums[i] > local + nums[i]){
                start = i;
                local = nums[i];
            }else{
                local += nums[i];
            }
            if (local > global){
                end = i;
                global = local;
                index[0] = start;
                index[1] = end;
            }
        }
        int len = index[1] - index[0] + 1;
        int[] res = new int[len];
        for (int i = index[0]; i <= index[1]; i++){
            res[i-index[0]] = nums[i];
        }
        return res;
    }
}

class Solution {
    public int[] maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int end = 0;
        int[] index = new int[2];
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (sum > max){
                end = i;
                max = sum;
                index[0] = start;
                index[1] = end;
            }
            if (sum < 0){
                sum = 0;
                start = i + 1;
            }
        }
        int len = index[1] - index[0] + 1;
        int[] res = new int[len];
        for (int i = index[0]; i <= index[1]; i++){
            res[i-index[0]] = nums[i];
            System.out.println(nums[i]);
        }
        return max;
    }
}
https://github.com/optimisea/Leetcode/blob/master/Java/402_Continuous.java
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
