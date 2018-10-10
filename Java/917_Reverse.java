Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

 

Example 1:

Input: "ab-cd"
Output: "dc-ba"
Example 2:

Input: "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"
 

Note:

S.length <= 100
33 <= S[i].ASCIIcode <= 122 
S doesn't contain \ or "

Method 1: Two pointers
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right){
            while (left < right && !Character.isLetter(chars[left])){
                left++;
            }
            while (left < right && !Character.isLetter(chars[right])){
                right--;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
}

Method 2:
class Solution {
    public String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder(S);
        int left = 0;
        int right = S.length() - 1;
        while (left < right){
            while (left < right && !Character.isLetter(sb.charAt(left))){
                left++;
            }
            while (left < right && !Character.isLetter(sb.charAt(right))){
                right--;
            }
            sb.setCharAt(left, S.charAt(right));
            sb.setCharAt(right, S.charAt(left));
            left++;
            right--;
        }
        return sb.toString();
    }
}

