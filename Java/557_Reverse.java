
Given a string, you need to reverse the order of characters in each word within a sentence while 
still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

Method 1: general solution for multiple spaces, and/or leading trailing sapce between words
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int start = 0;
        int i = 0;
        int n = s.length();
      //  s = s.trim().split("\\s+");
        char[] charArray = s.toCharArray();
        
        while (i < charArray.length){
            while (i < n && charArray[i] == ' '){
                i++;
            }
            start = i;
            while (i <n && charArray[i] != ' '){
                i++;
            }
            reverse(charArray, start, i-1);
        }
        return String.valueOf(charArray);
    }
    private void reverse(char[] charArray, int i, int j){
        while (i < j){
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
    }
}

Method 2: similar as reverse string II for one space between words
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int start = 0;
        int i = 0;
        int n = s.length();
      //  s = s.trim().split("\\s+");
        char[] charArray = s.toCharArray();
        
        while (i <= charArray.length){
            if (i == s.length() || charArray[i] == ' '){
                reverse(charArray, start, i-1);
                start = i + 1;
            }
            i++;
        }
        return String.valueOf(charArray);
    }
    private void reverse(char[] charArray, int i, int j){
        while (i < j){
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
    }
}

Method 3:
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < arr.length; i++){
            StringBuilder temp = new StringBuilder(arr[i]);
            sb.append(temp.reverse().toString());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}

https://leetcode.com/articles/reverse-words-in-a-string/
