Given a List of words, return the words that can be typed using letters of alphabet on only one 
row's of American keyboard like the image below.

class Solution {
    public String[] findWords(String[] words) {
        String[] strs = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0 ; i < strs.length; i++){
            for (int j = 0; j < strs[i].length(); j++){
                map.put(strs[i].charAt(j), i);
            }
        }
        List<String> list = new ArrayList<>();
        for (String word : words){
            int num = map.get(word.toUpperCase().charAt(0));
            boolean can = true;
            for (int i = 1; i < word.length(); i++){
                if (map.get(word.toUpperCase().charAt(i)) != num){
                    can = false;
                    break;
                }
            }
            if(can){
                list.add(word);
            }
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
        
    }
}
