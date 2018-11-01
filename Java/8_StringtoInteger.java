8. String to Integer (atoi)

Implement atoi to convert a string to an integer.

class Solution {
    public int myAtoi(String str) {
        if (str == null){
            return 0;
        }
        str = str.trim();
        int i = 0;
        char flag = '+';
        if (str.length() == 0){
            return 0;
        }
        if (str.charAt(0) == '-'){
            flag = '-';
            i++;
        }else if (str.charAt(0) == '+'){
            i++;
        }
        double res = 0;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'){
            res = res * 10 + (int)(str.charAt(i) - '0');
            i++;
        }
        if (flag == '-'){
            res = -res;
        }
        if (res < Integer.MIN_VALUE){
            res = (int) Integer.MIN_VALUE;
        }
        if (res > Integer.MAX_VALUE){
            res = (int) Integer.MAX_VALUE;
        }
        return (int)res;
    }
}
