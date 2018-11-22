Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Example :

Input: 11
Output: 3
Explanation: the 32-bit integer 11 has binary representation 00000000000000000000000000001011 .

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0){
            n = n & (n-1);
            count++;
        }
        return count;
    }
}

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++){
            if (((n >> i) & 1) == 1){
                count++;
            }
        }
        return count;
    }
}
