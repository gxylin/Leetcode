Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5

https://leetcode.com/articles/number-of-segments-in-a-string/
Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int countSegments(String s) {
        if (s == null){
            return 0;
        }
        String trim = s.trim();
        if (trim.equals("")){
            return 0;
        }
        return trim.split("\\s+").length;
    }
}

Method 2: Good solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            if ((i == 0 || s.charAt(i-1) == ' ') && s.charAt(i) != ' '){
                count++;
            }
        }
        return count;
    }
}
