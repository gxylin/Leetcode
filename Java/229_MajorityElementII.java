Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int major1 = 0;
        int major2 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == major1){
                count1++;
            }else if (nums[i] == major2){
                count2++;
            }else if (count1 == 0){
                major1 = nums[i];
                count1++;
            }else if (count2 == 0){
                major2 = nums[i];
                count2++;
            }else{
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == major1){
                count1++;
            }else if (nums[i] == major2){
                count2++;
            }
        }
        if (count1 > nums.length / 3){
            res.add(major1);
        }
        if (count2 > nums.length / 3){
            res.add(major2);
        }
        return res;
    }
}
