Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.


class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int sum = 0;
        int carry = 0;
        int digit = 0;
        while (i >= 0 && j >= 0){
            sum = carry + (int) (num1.charAt(i) - '0') + (int) (num2.charAt(j) - '0');
            carry = sum / 10;
            digit = sum % 10;
            sb.append(digit);
            i--;
            j--;
        }
        if (i >= 0){
            while (i >= 0){
                sum = carry + (int) (num1.charAt(i) - '0');
                carry = sum / 10;
                digit = sum % 10;
                sb.append(digit);
                i--;
            }
        }else{
             while (j >= 0){
                sum = carry + (int) (num2.charAt(j) - '0');
                carry = sum / 10;
                digit = sum % 10;
                sb.append(digit);
                j--;
            }
        }
        if (carry > 0){
            sb.append(carry);
        }
        return sb.reverse().toString();
        
    }
}
