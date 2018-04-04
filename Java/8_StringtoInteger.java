8. String to Integer (atoi)

Implement atoi to convert a string to an integer.

class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0){
            return 0;
        }
        str = str.trim();
        int i = 0;
        char flag = '+';
        if (str.charAt(i) == '-'){
            flag = '-';
            i++;
        }else if (str.charAt(i) == '+'){
            i++;
        }
        double result = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <='9'){
            result = result * 10 + (int) (str.charAt(i) - '0');
            i++;
        }
        if (flag == '-'){
            result = -result;
        }
        if (result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        if (result > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }
}
