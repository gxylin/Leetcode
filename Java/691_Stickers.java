We are given N different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given target string by cutting individual letters from your collection of stickers and rearranging them.

You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

What is the minimum number of stickers that you need to spell out the target? If the task is impossible, return -1.

Example 1:

Input:

["with", "example", "science"], "thehat"
Output:

3
Explanation:

We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
Example 2:

Input:

["notice", "possible"], "basicbasic"
Output:

-1
Explanation:

We can't form the target "basicbasic" from cutting letters from the given stickers.
Note:

stickers has length in the range [1, 50].
stickers consists of lowercase English words (without apostrophes).
target has length in the range [1, 15], and consists of lowercase English letters.
In all test cases, all words were chosen randomly from the 1000 most common US English words, and the target was chosen as a concatenation of two random words.
The time limit may be more challenging than usual. It is expected that a 50 sticker test case can be solved within 35ms on average.

Method: DP + backtracking
https://leetcode.com/problems/stickers-to-spell-word/discuss/108318/C++JavaPython-DP-+-Memoization-with-optimization-29-ms-(C++)

class Solution {
    public int minStickers(String[] stickers, String target) {
        int m = stickers.length;
        int[][] stickerMap = new int[m][26];
        for (int i = 0; i < m; i++){
            String s = stickers[i];
            for (int j = 0; j < s.length(); j++){
                stickerMap[i][s.charAt(j) - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return minStickers(stickerMap, target, dp);
    }
    private int minStickers(int[][] stickerMap, String target, Map<String, Integer> dp){
        if (dp.containsKey(target)){
            return dp.get(target);
        }
        int[] targetMap = new int[26];
        for (int i = 0; i < target.length(); i++){
            targetMap[target.charAt(i) - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < stickerMap.length; i++){
            if (stickerMap[i][target.charAt(0) - 'a'] == 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++){
                if (targetMap[j] > 0){
                    for (int k = 0; k < Math.max(0, targetMap[j] - stickerMap[i][j]); k++){
                        sb.append((char)(j + 'a'));
                    }
                }
            }
            String str = sb.toString();
            int min = minStickers(stickerMap, str, dp);
            if (min != -1){
                ans = Math.min(ans, min + 1);
            }
        }
        if (ans == Integer.MAX_VALUE){
            ans = -1;
        }
        dp.put(target, ans);
        return ans;
    }
}
