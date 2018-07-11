Find the largest palindrome made from the product of two n-digit numbers.

Since the result could be very large, you should return the largest palindrome mod 1337.

Example:

Input: 2

Output: 987

Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:

The range of n is [1,8].


The idea of this question is greedy:

    find the max number that product of two number
    try first largest palindrome, then second ...
    verify if the palindrome can be formed by production of two i digit number.
    repeat this until the palindrome has been found.


class Solution {
    public int largestPalindrome(int n) {
        if (n == 1){
            return 9;
        }
        long maxNum = (long) Math.pow(10, n) - 1;
        long minNum = (long) Math.pow(10, n-1);
        long maxProd = maxNum * maxNum;
        long firstHalf = maxProd / (long) Math.pow(10, n);
        while (firstHalf > minNum){
            long pa = createPa(firstHalf);
            for (long i = maxNum; i >= minNum && pa / i <= maxNum; i--){
                if (pa % i == 0){
                    return (int) (pa % 1337);
                }
            }
            firstHalf--;
        }
        return -1;
    }
    private long createPa(long num){
        String s = num + new StringBuilder().append(num).reverse().toString();
        return Long.parseLong(s);
    }
}
