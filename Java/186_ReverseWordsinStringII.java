 Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

class Solution {
    public void reverseWords(char[] str) {
        if (str == null || str.length == 0){
            return;
        }
        int  start = 0;
        int i = 0;
        for (i = 0; i < str.length; i++){
            if (str[i] == ' '){
                reverse(str, start, i - 1);
                start = i + 1;
            }
        }
        reverse(str, start, i - 1);
        reverse(str, 0, str.length - 1);
    }
    private void reverse(char[] str, int start, int end){
        while (start < end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}
