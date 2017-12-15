Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Notice

Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.

Have you met this question in a real interview? Yes
Example
Example 1:

Given s = "internationalization", abbr = "i12iz4n":
Return true.
Example 2:

Given s = "apple", abbr = "a2e":
Return false.

public class Solution {
    /*
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] wordChar = word.toCharArray();
        char[] abbrChar = abbr.toCharArray();
        
        int i = 0, j = 0;
        while (i < wordChar.length && j < abbrChar.length){
            if (Character.isDigit(abbrChar[j])){
                if (abbrChar[j] == '0'){
                    return false;
                }
                int val = 0;
                while (j < abbrChar.length && Character.isDigit(abbrChar[j])){
                    val = val * 10 + abbrChar[j] - '0';
                    j++;
                }
                i += val;
            }else{
                if (wordChar[i++] != abbrChar[j++]){
                   return false; 
                }
            }
        }
        return i == wordChar.length;
    }
}
