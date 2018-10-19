Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output:  321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range. 
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Method:
Note the difference of negative modulo between Java and Python which is the same as Math definition
In Java: -5 % 3 = -2
In Python: -5 % 3 = 1
Math definiition: a % n = a - floor(a/n) * n

https://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers


class Solution {
    public int reverse(int x) {
        long ans = 0;
        while (x != 0){
            ans = ans * 10 + x % 10;
            x /= 10;
            if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int)ans;   
    }
}

class Solution {
    public int reverse(int x) {
        int ans = 0;
        while (x != 0){
            int newResult = 10 * ans + x % 10;
            if ((newResult - x % 10) / 10 != ans){
                return 0;
            }
            ans = newResult;
            x /= 10;
        }
        return ans;
    }
}


Best solution:
class Solution {
    public int reverse(int x) {
        long res = 0;
        while (x != 0){
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        return (int) res == res ? (int)res : 0;
    }
}
