Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, 
and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.

The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.  For example, 
the complement of "101" in binary is "010" in binary.

For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.

 

Example 1:

Input: 5
Output: 2
Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
Example 2:

Input: 7
Output: 0
Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
Example 3:

Input: 10
Output: 5
Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
 

Note:

0 <= N < 10^9
https://leetcode.com/problems/complement-of-base-10-integer/discuss/256740/JavaC%2B%2BPython-Find-111.....1111-greater-N


Best solution 1 :
N + bitwiseComplement(N) = 11....11 = X
Then bitwiseComplement(N) = X - N

class Solution {
    public int bitwiseComplement(int N) {
        int X = 1;
        while (N > X){
            X = X * 2 + 1;
        }
        return X - N;
    }
}

Best soluion 2:
N ^ bitwiseComplement(N) = 11....11 = X
bitwiseComplement(N) = N ^ X
class Solution {
    public int bitwiseComplement(int N) {
        int X = 1;
        while (N > X){
            X = X * 2 + 1;
        }
        return X  ^ N;
    }
}


Method 1: find the first 
class Solution {
    public int bitwiseComplement(int N) {
        if (N == 0){
            return 1;
        }
        int count = 0;
        int M = N;
        while (M > 0){
            M /= 2;
            count++;
        }
        int res = 0;
        for (int i = 0; i < count; i++){
            res |= (1 ^ ((N >> i) & 1)) << i;
        }
        return res;
    }
}

Method 2:
class Solution {
    public int bitwiseComplement(int N) {
        if (N == 0){
            return 1;
        }
        int res = 0;
        int curr = 1;
        while (N != 0){
            if (N % 2 == 0){
                res += curr;
            }
            N /= 2;
            curr *= 2;
        }
        return res;
    }
}
