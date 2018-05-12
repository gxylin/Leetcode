Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.

Method 1: counting
class Solution {
    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++){
            if (Character.isUpperCase(word.charAt(i))){
                count++;
            }
        }
        return (count == 0 || count == word.length() || (count == 1 && Character.isUpperCase(word.charAt(0))));
    }
}

Method 2:
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() < 2){
            return true;
        }
        if (word.substring(1).equals(word.substring(1).toLowerCase())){
            return true;
        }
        if (word.equals(word.toUpperCase())){
            return true;
        }
        return false;
    }
}

Method 3:
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0){
            return false;
        }
        boolean startCap = false;
        if (word.charAt(0) - 'A' < 26){
            startCap = true;
        }
        boolean secondCap = false;
        for (int i = 1; i < word.length(); i++){
            if (!startCap){
                if (word.charAt(i) - 'A' < 26)
                    return false;
            }else{
                if (i == 1){
                    if (word.charAt(1) - 'A' < 26)
                        secondCap = true;
                }else{
                    if (!secondCap){//second one is lower case
                        if (word.charAt(i) - 'A' < 26)
                            return false;
                    }else{//second one is upper case
                        if (word.charAt(i) - 'A' > 26)
                            return false;
                    }
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0){
            return false;
        }
        boolean startCap = false;
        if (Character.isUpperCase(word.charAt(0))){
            startCap = true;
        }
        boolean secondCap = false;
        for (int i = 1; i < word.length(); i++){
            if (!startCap){
                if (Character.isUpperCase(word.charAt(i)))
                    return false;
            }else{
                if (i == 1){
                    if (Character.isUpperCase(word.charAt(1)))
                        secondCap = true;
                }else{
                    if (!secondCap){//second one is lower case
                        if (Character.isUpperCase(word.charAt(i)))
                            return false;
                    }else{//second one is upper case
                        if (Character.isLowerCase(word.charAt(i)))
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
