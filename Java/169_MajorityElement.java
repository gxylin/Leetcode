Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Method 1:
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer num : map.keySet()){
            if (map.get(num) > nums.length / 2){
                return num;
            }
        }
        return Integer.MIN_VALUE;
    }
}

Method 2:
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

Method 3:
class Solution {
    public int majorityElement(int[] nums) {
        int major = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == major){
                count++;
            }else if (count == 0){
                major = nums[i];
                count++;
            }else{
                count--;
            }
        }
        return major;
    }
}
