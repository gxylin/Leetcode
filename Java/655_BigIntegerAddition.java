Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

 Notice
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
Have you met this question in a real interview? Yes
Example
Given num1 = "123", num2 = "45"
return "168"

public class Solution {
    /*
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null){
            return null;
        }
        int n = num1.length();
        int m = num2.length();
        
        String ans = "";
        int carry = 0;
        int sum;
        for (int i = n-1, j = m-1; i >= 0 || j >=0 ; i--,j --){
            sum = carry;
            sum += (i >= 0) ? num1.charAt(i) - '0' : 0;
            sum += (j >= 0) ? num2.charAt(j) - '0' : 0;
            ans = sum % 10 + ans;
            carry = sum / 10;
        }
        if (carry != 0){
            ans = carry + ans;
        }
        return ans;
    }
}
