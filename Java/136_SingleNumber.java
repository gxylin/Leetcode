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
