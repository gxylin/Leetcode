Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within
the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that 
character three times in the final answer.

You may return the answer in any order.

class Solution {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        String first = A[0];
        int len = A.length;
        int[] hashA = new int[26];
        for (char c : first.toCharArray()){
            hashA[c - 'a']++;
            if (len == 1){
                res.add(String.valueOf(c));
            }
        }
        if (len == 1){
            return res;
        }
        for (int i = 1; i < len; i++){
            String str = A[i];
            int[] hash = new int[26];
            for (char c : str.toCharArray()){
                hash[c - 'a']++;
            }
            for (int j = 0; j < 26; j++){
                hashA[j] = Math.min(hashA[j], hash[j]);
            }
        }
        for (int i = 0; i < 26; i++){
            while (hashA[i] > 0){
                res.add(String.valueOf((char)(i + 'a')));
                hashA[i]--;
            }
        }
        return res;
    }
}
