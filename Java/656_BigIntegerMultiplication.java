Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2

Have you met this question in a real interview? Yes
Example
The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

高精度乘法的实现
对应数位相加后一次性进位

public class Solution {
    /*
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        
        int[] ans = new int[l1 + l2 + 1];
        
        for (int i = 0; i < l1; i++){
            for (int j = 0; j < l2; j++){
                ans[i + j] += (num1.charAt(l1 - 1 - i) - '0') * (num2.charAt(l2 - 1 - j) - '0');
            }
        }
        for (int i = 0; i < l1 + l2; i++){
            ans[i + 1] += ans[i] / 10;
            ans[i] = ans[i] % 10;
        }
        int i = l1 + l2;
        while (ans[i] == 0 && i >= 1){
            i--;
        }
        String str = "";
        while (i >= 0){
            str = str + ans[i--];
        }
        return str;
    }
}
