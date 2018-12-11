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

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int sum = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >=0 || j>= 0; i--, j--){
            sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            carry = sum / 2;
            sb.insert(0, sum %2);
        }
        if (carry != 0){
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}

Best solution:
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0){
            int numA = i >= 0 ? (int)(a.charAt(i) - '0') : 0;
            int numB = j >= 0 ? (int)(b.charAt(j) - '0') : 0;
            int digit = numA + numB + carry;
            sb.append(digit%2);
            carry = digit/2;
            i--;
            j--;
        }
        if (carry != 0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
