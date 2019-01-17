Given an integer, convert it to a roman numeral.

The number is guaranteed to be within the range from 1 to 3999.

Have you met this question in a real interview? Yes
Clarification
What is Roman Numeral?

https://en.wikipedia.org/wiki/Roman_numerals
https://zh.wikipedia.org/wiki/%E7%BD%97%E9%A9%AC%E6%95%B0%E5%AD%97
http://baike.baidu.com/view/42061.htm
Example
4 -> IV

12 -> XII

21 -> XXI

99 -> XCIX

more examples at: http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm

• 如何数位分离? %10 /10 
• (扩展)如何将一个数转成k进制? % k / k

Method 1: best solution
public class Solution {
    /*
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        if (n <= 0){
            return "";
        }
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    StringBuilder sb = new StringBuilder();
	    int digit = 0;
	    while (n > 0){
	        int times = n / nums[digit];
	        for (int i = 0; i < times; i++){
	            sb.append(symbols[digit]);
	        }
	        n = n % nums[digit];
	        digit++;
	    }
	    return sb.toString();
    }
}

Method 2:
public class Solution {
    /**
     * @param n The integer
     * @return Roman representation
     */
    public String intToRoman(int n) {
        // Write your code here
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[n / 1000] + C[(n / 100) % 10] + X[(n / 10) % 10] + I[n % 10];
    }
}
