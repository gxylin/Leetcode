Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Method:
Use XOR
XOR has Commutative property and associative property
a ^ a = 0,  a ^ 0 = a

class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int n : nums){
            ans ^= n;
        }
        return ans;
    }
}


Generalization format as Single II 
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++){
            int sum = 0;
            for (int j = 0; j < nums.length; j++){
                if (((nums[j] >> i) & 1) == 1){
                    sum++;
                    sum %= 2;
                }
            }
            if (sum != 0){
                res |= (sum << i);
            }
        }
        return res;
    }
}


class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++){
            int temp = 0;
            for (int num : nums){
                temp += ((num >> i) & 1);
            }
            if (temp % 2 == 1){
                res |= (1 << i);
            }
        }
        return res;
    }
}
