Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
 
 Method 1:
 class Solution {
    public int titleToNumber(String s) {
        int times = 1;
        int weight = 26;
        int ans = 0;
        for (int i = s.length() - 1; i >= 0; i--){
            int num = s.charAt(i) - 'A' + 1;
            ans += num * times;
            times *= weight;
        }
        return ans;
    }
}

Method 2:
class Solution {
    public int titleToNumber(String s) {
        int ans = 0;
        int weight = 26;
        for (int i = 0; i < s.length(); i++){
            ans = ans * weight + s.charAt(i) - 'A' + 1;
        }
        return ans;
    }
}
