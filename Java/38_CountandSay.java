The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"


class Solution {
    public String countAndSay(int n) {
        StringBuilder cur = new StringBuilder("1");
        StringBuilder prev = new StringBuilder();
        for (int i = 1; i < n; i++){
            prev = cur;
            cur = new StringBuilder();
            char say = prev.charAt(0);
            int count = 1;
            for (int j = 1; j < prev.length(); j++){
                if (prev.charAt(j) != say){
                    cur.append(String.valueOf(count)).append(say);
                    say = prev.charAt(j);
                    count = 1;
                }else{
                    count++;
                }
            }
            cur.append(String.valueOf(count)).append(say);
        }
        return cur.toString();
    }
}


Better version:
class Solution {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 1; i < n; i++){
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (j < str.length()){
                char c = str.charAt(j);
                int k = j + 1;
                int times = 1;
                while (k < str.length() && str.charAt(k) == c){
                    k++;
                    times++;
                }
                sb.append(times);
                sb.append(c);
                j = k;
            }
            str = sb.toString();
        }
        return str;
    }
}
