Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".

 

Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
Example 2:

Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
Example 3:

Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4
 

Note:

0 <= N <= 10^9

https://www.geeksforgeeks.org/convert-number-negative-base-representation/
class Solution {
    public String baseNeg2(int N) {
        //  If n is zero then in any base it will be 0 only 
        if (N == 0){
            return "0";
        }
        String converted = "";
        
        while (N != 0){
            // Get remainder by negative base, it can be negative also 
            int remainder = N % (-2); 
            N /= -2; 

            // if remainder is negative, add abs(base) to it and add 1 to n 
            if (remainder < 0){
                remainder += 2; 
                N += 1; 
            } 
            // convert remainder to string add into the result 
            converted = String.valueOf(remainder) + converted; 
        }
        return converted;
    }
}

class Solution {
    public String baseNeg2(int N) {
        //  If n is zero then in any base it will be 0 only 
        if (N == 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (N != 0){
            // Get remainder by negative base, it can be negative also 
            int remainder = N % (-2); 
            N /= -2; 

            // if remainder is negative, add abs(base) to it and add 1 to n 
            if (remainder < 0){
                remainder += 2; 
                N += 1; 
            } 
            // convert remainder to string add into the result 
            sb.append(remainder);
        }
        return sb.reverse().toString();
    }
}


5 % 2 = 1
5 % (-2) = -1
(-5) % 2 = -1
(-5) % (-2) = 1
Note that the sign of the result equals the sign of the dividend.

Says it in Java specs:

https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.3


