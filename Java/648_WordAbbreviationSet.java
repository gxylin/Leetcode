An abbreviation of a word follows the form . Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Have you met this question in a real interview? Yes
Example
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") // return false
isUnique("cart") // return true
isUnique("cane") // return false
isUnique("make") // return true

public class ValidWordAbbr {
    private Map<String, Integer> dict = new HashMap<>();
    private Map<String, Integer> abbr = new HashMap<>();
    /*
    * @param dictionary: a list of words
    */public ValidWordAbbr(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++){
            String s = dictionary[i];
            if (!dict.containsKey(s)){
                dict.put(s, 1);
            }else{
                dict.put(s, dict.get(s) + 1);
            }
            
            String a = getAbbr(s);
            if (!abbr.containsKey(a)){
                abbr.put(a, 1);
            }else{
                abbr.put(a, abbr.get(a) + 1);
            }
        }
    }

    /*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        String ab = getAbbr(word);
        return dict.get(word) == abbr.get(ab);
    }
    
    private String getAbbr(String word){
        if (word.length() <= 2){
            return word;
        }
        //use Integer.toString
        return (word.charAt(0) + Integer.toString(word.length() - 2) +   
                        word.charAt(word.length() - 1));
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param = obj.isUnique(word);
 */
