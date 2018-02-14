Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.

class Solution {
    public int getSum(int a, int b) {
        if (a == 0){
            return b;
        }
        if (b == 0){
            return a;
        }
        while (b != 0){
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
}

https://leetcode.com/problems/sum-of-two-integers/discuss/84290/Java-simple-easy-understand-solution-with-explanation

public int getSum(int a, int b) {
     if(b == 0){//没有进为的时候完成运算
        return a;
    }
    int sum,carry;
    sum = a^b;//完成第一步加发的运算
    carry = (a&b)<<1;//完成第二步进位并且左移运算
    return getSum(sum,carry);//
    }
    
    
