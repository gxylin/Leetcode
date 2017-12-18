Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Have you met this question in a real interview? Yes
Example
Given nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
return ["2", "4->49", "51->74", "76->99"].

思路:
• 简单的模拟题
– 两端点和一头一尾形成的区间 + for循环扫描中间形成的区间 – 利用函数让自己的代码更简洁(见代码)
• 特殊输入?
– 实现时可能出现中间值超过int范围

public class Solution {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        if (nums == null || nums.length == 0){
            getRange(result, lower, upper);
            return result;
        }
        if (nums[0] != Integer.MIN_VALUE){
           getRange(result, lower, nums[0] - 1); 
        }
        for (int i = 1; i < nums.length; i++){
            if (nums[i-1] != Integer.MAX_VALUE && nums[i] != Integer.MIN_VALUE){
              getRange(result, nums[i-1] + 1, nums[i] - 1);  
            }
        }
        if (nums[nums.length - 1] != Integer.MAX_VALUE){
          getRange(result, nums[nums.length - 1] + 1, upper);  
        }
        return result;
    }
    private void getRange(List<String> result, int lower, int upper){
        if (lower > upper){
            return;
        }else if (lower == upper){
            result.add(String.valueOf(lower));
        }else{
            result.add(lower + "->" + upper);
        }
    }
}
