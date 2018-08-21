Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation: 
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
Note: 1 <= n <= 109


Method 1: Brute Force  TLE
Time complexity: O(32 * n)
class Solution {
    public int findIntegers(int num) {
        int count = 0;
        for (int i = 0; i <= num; i++){
            if (check(i)){
                count++;
            }
        }
        return count;
    }
    private boolean check(int n){
        int i = 31;
        while (i > 0){
            if ((n & (1 << i)) != 0 && (n & (1 << (i-1))) != 0){
                return false;
            }
            i--;
        }
        return true;
    }
}

Method 2: DP
Time complexity: O(32)
Space complexity: O(32)
https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103749/Java-Solution-DP
https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
class Solution {
    public int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num));
        int len = sb.length();
        int[] zeros = new int[len];
        int[] ones = new int[len];
        zeros[0] = 1;
        ones[0] = 1;
        for (int i = 1; i < len; i++){
            zeros[i] = zeros[i-1] + ones[i-1];
            ones[i] = zeros[i-1];
        }
        int ans = zeros[len-1] + ones[len-1];
        //remove cases which are greater than num
        for (int i = 1; i < len; i++){
            if (sb.charAt(i) == '1' && sb.charAt(i-1) == '1'){
                break;
            }
            if (sb.charAt(i) == '0' && sb.charAt(i-1) == '0'){
                 ans -= ones[len-i-1];
            }
        }
        return ans;
    }
}

Why break the loop when seeing a pair of 11, and minus b[i] when seeing a pair of 00? I list out the n=5 sequence. X means containing 11.

0 00000
1 00001
2 00010
3 00011 X
4 00100
5 00101
6 00110 X
7 00111 X
8 01000
9 01001
10 01010
11 01100 X
12 01100 X
13 01101 X
14 01110 X
15 01111 X
16 10000
17 10001
18 10010
19 10011 X
20 10100
21 10101
22 10110 X
23 10111 X
24 11000 X
25 11001 X
26 11010 X
27 11011 X
28 11100 X
29 11101 X
30 11110 X
31 11111 X

Now, say num = 16 (i.e. 10000)

From the most significant digit onward, look at each pair of the digits (i.e. 10, 00, 00, 00)
The first pair of 00 ==> delete 10100 & 10101
The second pair of 00 ==> delete 10010
The third pair of 00 ==> delete 10001
