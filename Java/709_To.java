Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

 

Example 1:

Input: "Hello"
Output: "hello"
Example 2:

Input: "here"
Output: "here"
Example 3:

Input: "LOVELY"
Output: "lovely"

class Solution {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (c <= 'Z' && c >= 'A'){
                sb.append((char)(c - 'A' + 'a'));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
