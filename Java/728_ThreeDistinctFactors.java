Given a positive integer n (1 <= n <= 10^18). Check whether a number has exactly three distinct factors, 
return true if it has exactly three distinct factors, otherwise false.

Have you met this question in a real interview? Yes
Example
Given n = 9, return true
Number 9 has exactly three factors: 1, 3, 9, so return true.

Given n = 10, return false

Reference:
https://www.geeksforgeeks.org/find-divisors-natural-number-set-1/
https://stackoverflow.com/questions/9898512/how-to-test-if-a-double-is-an-integer

Method:
Time complexity: O(sqrt(n))
public class Solution {
    /*
     * @param n: the given number
     * @return: true if it has exactly three distinct factors, otherwise false
     */
    public boolean isThreeDisctFactors(long n) {
        int count = 0;
        for (long i = 2; i < Math.sqrt(n)+1; i++){
            if (n % i == 0 && i != Math.sqrt(n)){
                return false;
            }
        }
        return true;
    }
}
