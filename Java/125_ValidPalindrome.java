Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < s.length() && r >= 0 && l < r){
            while (l < s.length() && !Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }
            while (r >= 0 && !Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }
            if (l < r){
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
}


Better version:
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0){
            return true;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(chars[left])){//isLetterOrDigit: alphanumeric
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(chars[right])){
                right--;
            }

                if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])){//ignore cases
                    return false;
                }
                left++;
                right--;
            
        }
        return true;
    }
}
