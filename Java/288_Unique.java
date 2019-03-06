An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation
     is unique if no other word from the dictionary has the same abbreviation.

Example:

Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

Method:
class ValidWordAbbr {
    Map<String, String> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary){
            String key = getAbbr(s);
            if (map.containsKey(key)){
                if (!map.get(key).equals(s)){
                    map.put(key, "");
                }
            }else{
                map.put(key, s);
            }
        }
    }
    
    public boolean isUnique(String word) {
        return !map.containsKey(getAbbr(word)) || map.get(getAbbr(word)).equals(word);
    }
    private String getAbbr(String word){
        int n = word.length();
        if (n <= 2){
            return word;
        }
        return word.charAt(0) + String.valueOf(n - 2) + word.charAt(n - 1);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
