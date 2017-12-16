Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
 Notice

Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
Have you met this question in a real interview? Yes
Example
Given dict = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
return ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]

public class Solution {
    /*
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        int len = dict.length;
        String[] result = new String[len];
        int[] prefixNum = new int[len];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++){
            prefixNum[i] = 1;
            result[i] = getAbbr(dict[i], 1);
            if (!map.containsKey(result[i])){
                map.put(result[i], 1);
            }else{
                map.put(result[i], map.get(result[i]) + 1);
            }
        }
        boolean unique = false;
        while (!unique){
            unique = true;
            for (int i = 0; i < len; i++){
                if (map.get(result[i]) > 1){
                    prefixNum[i]++;
                    result[i] = getAbbr(dict[i], prefixNum[i]);
                    if (!map.containsKey(result[i])){
                        map.put(result[i], 1);
                    }else{
                        map.put(result[i], map.get(result[i]) + 1);
                    }
                    unique = false;
                }
            }
        }
        return result;
    }
    private String getAbbr(String str, int prefixNum){
        if (prefixNum >= str.length() - 2){
            return str;
        }
        String ans = str.substring(0, prefixNum) + (str.length() - prefixNum - 1)
                    + str.charAt(str.length() - 1);
        return ans;
    }
}
