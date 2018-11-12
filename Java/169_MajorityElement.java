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
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > nums.length / 2){
                return nums[i];
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

Method 3: Best solution:
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            if (count == 0){
                res = nums[i];
                count++;
            }else if (res == nums[i]){
                count++;
            }else{
                count--;
            }
        }
        return res;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++){
            if (count == 0){
                res = nums[i];
                count = 1;
            }else if (nums[i] == res){
                count++;
            }else{
                count--;
            }
        }
        return res;
    }
}

Method 4: Similar as Single Number II
bit manipulation:
class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++){
            int ones = 0;
            int zeros = 0;
            for (int j = 0; j < nums.length; j++){
                if ((nums[j] & (1 << i)) != 0){
                    ones++;
                }else{
                    zeros++;
                }
            }
            if (ones > zeros){
                res |= (1 << i);
            }
        }
        return res;
    }
}
