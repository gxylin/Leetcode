Given a positive integer, output its complement number. The complement strategy is to flip the bits of 
its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. 
So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

https://leetcode.com/problems/number-complement/discuss/95992/Java-1-line-bit-manipulation-solution

class Solution {
    public int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        return num & mask;
    }
}

Create a bit mask which has N bits of 1 from RIGHTMOST. In above example, the mask is 111. 
And we can use the decent Java built-in function Integer.highestOneBit to get the LEFTMOST bit of 1, left shift one, 
and then minus one.
Please remember this wonderful trick to create bit masks with N ones at RIGHTMOST, you will be able to use it later.
