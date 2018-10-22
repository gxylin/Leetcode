Given a roman numeral, convert it to an integer.

The answer is guaranteed to be within the range from 1 to 3999.

Have you met this question in a real interview? Yes
Clarification
What is Roman Numeral?

https://en.wikipedia.org/wiki/Roman_numerals
https://zh.wikipedia.org/wiki/%E7%BD%97%E9%A9%AC%E6%95%B0%E5%AD%97
http://baike.baidu.com/view/42061.htm

思路:
• 从左往右加起来,比如XVII=10+5+1+1=17
• 那么像IV=4 IX=9 XL=40 XC=90 这样的怎么处理呢?
• 没有4 9 40 90 这种的,字母代表的数字从左往右是从大到小的
• 发现左边的如果小于右边的,就把左边的减去,比如CDXXI

public class Solution {
    /*
     * @param s: Roman representation
     * @return: an integer
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++){
            int temp = toInt(s.charAt(i));
            if (temp < toInt(s.charAt(i+1))){
               ans -= temp; 
            }else{
                ans += temp;
            }
        }
        return ans + toInt(s.charAt(s.length()-1));
    }
    private int toInt(char c){
        switch(c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}

Better version
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++){
            int curr = map.get(s.charAt(i));
            int next = map.get(s.charAt(i+1));
            if (next > curr){
                res -= curr;
            }else{
                res += curr;
            }
        }
        return res + map.get(s.charAt(s.length() - 1));
    }
}
