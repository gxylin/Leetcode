mplement pow(x, n).

 Notice
You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.

Have you met this question in a real interview? Yes
Example
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1
Challenge: O(logn) time

思路:
• 普通求幂的时间复杂度 O(n)
• 怎样更快的求幂?
￼• x1=x(1)=x1
• x2=x(10)=x2
• x3=x(11)=x2*x1
• x4=x(100)=x4
• x5=x(101)=x4*x1
• x6=x(110)=x4*x2
•x1=x1 •(x1)2=x2
•(x2)2=x4 •(x4)2=x8
问:7可以分解成哪 几个数的和?
十进制转二进制 %2 /2 时间复杂度O(logn)

public class Solution {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        if (n < 0){
            if (n == Integer.MIN_VALUE){
                return 0;
            }
            x = 1/x;
            n = -n;
        }
        double ans = 1;
        double tmp = x;
        while (n > 0){
            if (n % 2 == 1){
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }
        return ans;
    }
}
