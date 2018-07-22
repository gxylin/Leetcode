Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0

The key is to find common parts from left to right between m and n.
Note that the last bit of any odd number and any even number will be 0

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n){
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}


https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56729/Bit-operation-solution(JAVA)
