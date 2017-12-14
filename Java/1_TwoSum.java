1. Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

--------------------------------------------
Method 1: HashMap
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0){
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}

Method 2: Sort + two points
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int[] number = new int[2];
        if (nums == null || nums.length == 0){
            return result;
        }
        int[] origin = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            origin[i] = nums[i];
        }
        Arrays.sort(origin);
        int left = 0;
        int right = nums.length -1;
        while (left < right){
            int sum = origin[left] + origin[right];
            if (sum == target){
                number[0] = origin[left];
                number[1] = origin[right];
                break;
            }else if (sum < target){
                left++;
            }else{
                right--;
            } 
        }
       for (int i = 0; i < nums.length; i++){
           if (nums[i] == number[0]){
               result[0] = i;
               break;
           }
       }
       for (int i = nums.length - 1; i >= 0; i--){
           if (nums[i] == number[1]){
               result[1] = i;
               break;
           }
        }
        return result;
    }
   
}
