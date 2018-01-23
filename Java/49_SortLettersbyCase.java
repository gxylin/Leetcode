Given a string which contains only letters. Sort it by lower case first and upper case second.

 Notice
It's NOT necessary to keep the original order of lower-case letters and upper case letters.

Have you met this question in a real interview? Yes
Example
For "abAcD", a reasonable answer is "acbAD"

public class Solution {
    /*
     * @param chars: The letter array you should sort by Case
     * @return: nothing
     */
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0){
            return;
        }
        int start = 0;
        int end = chars.length - 1;
        while (start < end){
            while (start < end && Character.isLowerCase(chars[start])){
                start++;
            }
            while (start < end && Character.isUpperCase(chars[end])){
                end--;
            }
            if (start < end){
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
                start++;
                end--;
            }
        }
    }
}
