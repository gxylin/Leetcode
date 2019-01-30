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

class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] res = new int[l1+l2];
        for (int i = 0; i < l1; i++){
            for (int j = 0; j < l2; j++){
                res[i+j] += (int)(num1.charAt(l1-i-1) - '0') * (int)(num2.charAt(l2-j-1) - '0');
            }
        }
        for (int i = 0; i < res.length - 2; i++){
            res[i+1] += res[i] / 10;
            res[i] %= 10;
        }
        int i = l1+l2-1;
        while (i > 0 && res[i] == 0){
            i--;
        }
        StringBuilder sb = new StringBuilder();
        while (i >= 0){
            sb.append(res[i]);
            i--;
        }
        return sb.toString();
    }
}


Better version:
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = 0;
        int j = 0;
        int prev = 0;
        int len1 = num1.length();
        int len2 = num2.length();
        for (i = 0; i < len1; i++){
            int c1 = (int)(num1.charAt(len1-1-i) - '0');
            int digits = 0;
            int num = 0;
            carry = 0;
            for (j = 0; j < len2; j++){
                int c2 = (int)(num2.charAt(len2-1-j) - '0');
                if (sb.length() == i+j){
                    prev = 0;
                }else{
                    prev = (int)(sb.charAt(i+j) - '0');
                }
                digits = c1 * c2 + carry + prev;
                num = digits%10;
                carry = digits/10;
                if (sb.length() == i+j){
                    sb.append(num);
                }else{
                    sb.setCharAt(i+j, (char)(num + '0'));
                }
            }
            if (carry != 0){
                sb.append(carry);
            }
        }
        return sb.reverse().toString();
    }
}
