Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

class Solution {
    public int findNthDigit(int n) {
        int digits = 1;
        long factor = 9; // must use long because it may overflow
        int start = 1;
        while (n > digits * factor){
            n -= digits * factor;
            factor *= 10;
            digits += 1;
            start *= 10;
        }
        int pos = (n - 1) / digits; // -1 to convert to base 0
        int num = start + pos;
        int offset = (n- 1) % digits;
        String s = Integer.toString(num);
        return (int)(s.charAt(offset) -'0');
    }
}
