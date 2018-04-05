Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.


class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] ans = new int[l1+l2+1];
        for (int i = 0; i < l1; i++){
            for (int j = 0; j < l2; j++){
                ans[i+j] += (num1.charAt(l1 - 1 - i) - '0') * (num2.charAt(l2 - 1 - j) - '0');
            }
        }
        for (int i = 0; i < ans.length - 1; i++){
            ans[i+1] += ans[i]/ 10;
            ans[i] = ans[i] % 10;
        }
        StringBuilder sb = new StringBuilder();
        int i = l1+l2;
        while (ans[i] == 0 && i > 0){
            i--;
        }
        while (i >= 0){
            sb.append(ans[i]);
            i--;
        }
        return sb.toString();
    }
}
