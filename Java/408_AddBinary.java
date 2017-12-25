Given two binary strings, return their sum (also a binary string).

Have you met this question in a real interview? Yes
Example
a = 11

b = 1

Return 100

Method: Treat as 10 bit addition except for 2

public class Solution {
    /*
     * @param a: a number
     * @param b: a number
     * @return: the result
     */
    public String addBinary(String a, String b) {
        String ans = "";
        int carry = 0;
        int sum;
        for (int i = a.length()-1, j = b.length()-1; i>=0 || j>=0; i--,j--){
            sum = carry;
            sum += (i>=0) ? a.charAt(i) - '0' : 0;
            sum += (j>=0) ? b.charAt(j) - '0' : 0;
            ans = sum % 2 + ans;
            carry = sum / 2;
        }
        if (carry != 0){
            ans = carry + ans;
        }
        return ans;
    }
}
