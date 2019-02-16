Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 

Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.


Method 1:
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("[!?',;.]", "").toLowerCase().split("\\s+");
        int count = 0;
        String ans = "";
        for (String word : words){
            if (!set.contains(word)){
                map.put(word, map.getOrDefault(word, 0) + 1);
                if (count < map.get(word)){
                    count = map.get(word);
                    ans = word;
                }
            }
        }
        return ans;
    }
}

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        String[] words = paragraph.replaceAll("[!?',;.]", "").toLowerCase().split("\\s+");
        int count = 0;
        String ans = "";
        for (String word : words){
            if (!set.contains(word)){
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        for (String key : map.keySet()){
            if (count < map.get(key)){
                count = map.get(key);
                ans = key;
            }
        }
        return ans;
    }
}


Best solution:
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>();
        for (String s : banned){
            ban.add(s);
        }
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int max = 0;
        String res = "";
        paragraph += '.'; 
        for (char c : paragraph.toCharArray()){
            if (Character.isLetter(c)){
                sb.append(Character.toLowerCase(c));
            }else{
                if (sb.length() > 0){
                    String str = sb.toString();
                    sb = new StringBuilder();
                    if (!ban.contains(str)){
                        map.put(str, map.getOrDefault(str, 0) + 1);
                        int count = map.get(str);
                        if (count > max){
                            max = count;
                            res = str;
                        }
                    }
                    
                }
            }
        }
        return res;
    }
}
