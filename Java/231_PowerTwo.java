Given an integer, write a function to determine if it is a power of two.

Method 1:
time complexity: O(logn)
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        while (n > 1){
            if (n % 2 != 0){
                return false;
            }
            n /= 2;
        }
        return true;
    }
}

https://leetcode.com/problems/power-of-two/discuss/63966/4-different-ways-to-solve-Iterative-Recursive-Bit-operation-Math

Method 2:
time complexity: O(1)
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        return (n & (n-1)) == 0;
    }
}

Trick:
use (n&(n-1)) to check how many 1 in binary format
int count = 0;
while (n != 0){
  n = n & (n - 1);
  count++;
}
return count;
