Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:

Input: 9
Output: 10

Hint: n will not exceed 9 x 10^8. 

class Solution {
    public int newInteger(int n) {
        int res = 0;
        int base = 1;
        while (n > 0){
            res += n % 9 * base;
            n /= 9;
            base *= 10;
        }
        return res;
    }
}

class Solution {
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
}
the above change base is just a way to count... the actual reason why it works is ... every 10 number we have 9 numbers without '9' digit; every 100 number we have 9x9 numbers without '9' digit; 1000 number we have 9x9x9 numbers without '9' digit; .... did you see the pattern?
this pattern is essentially means we want a number n represent in base 9, what would the value of the same digit then if represent in base 10... so the above 1 lines change base method helps calculate this...
