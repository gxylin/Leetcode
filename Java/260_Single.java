Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?


https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C++Java-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations

class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int i = 0; i < nums.length; i++){
            diff ^= nums[i];
        }
       // diff &= -diff;
        diff = Integer.lowestOneBit(diff);
        int[] result = {0, 0};
        for (int i = 0; i < nums.length; i++){
            if ((nums[i] & diff) == 0){
                result[0] ^= nums[i];
            }else{
                result[1] ^= nums[i];
            }
        }
        return result;
    }
}

class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int i = 0; i < nums.length; i++){
            diff ^= nums[i];
        }
       // diff &= -diff;
       // diff = Integer.lowestOneBit(diff);
        int bit = 0;
        while (diff != 0){ // find the lowest bit 1
            if ((diff & 1) == 1){
                diff = (1 << bit);
                break;
            }
            diff = (diff >> 1);
            bit++;
        }
        int[] result = {0, 0};
        for (int i = 0; i < nums.length; i++){
            if ((nums[i] & diff) == 0){
                result[0] ^= nums[i];
            }else{
                result[1] ^= nums[i];
            }
        }
        return result;
    }
}



Better version:
class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums){
            diff ^= num;
        }
        int bit = 0;
        while (bit < 32){
            if (((diff >> bit) & 1) == 1){
                diff = (1 << bit);
                break;
            }
            bit++;
        }
        int[] res = new int[2];
        for (int num : nums){
            if ((diff & num) == 0){
                res[0] ^= num;
            }else{
                res[1] ^= num;
            }
        }
        return res;
    }
}


class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums){
            diff ^= num;
        }
        for (int i = 0; i < 32; i++){
            if (((diff >> i) & 1) == 1){
                diff = (1 << i);
                break;
            }
        }
        int first = 0;
        int second = 0;
        for (int num : nums){
            if ((num & diff) == 0){
                first ^= num;
            }else{
                second ^= num;
            }
        }
        return new int[]{first, second};
    }
}
