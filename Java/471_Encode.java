Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:

    k will be a positive integer and encoded string will not be empty or have extra space.
    You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
    If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.

Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.

Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.

Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".

Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".

Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".

    Similar Questions 
    Decode StringNumber of Atoms

Method: Dynamic Programming:
Time complexity: O(N^4) since replaceAll takes O(N) time

dp[i][j] = string from index i to index j in encoded form
diff means the length diff between i and j
diff is used because we only need to fill the top left half dp[i][j] matrix and return dp[0][n-1]

class Solution {
    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];//dp[i][j] = string from index i to index j in encoded form
        for (int diff = 0; diff < n; diff++){//diff means the length diff between i and j
            for (int i = 0; i < n - diff; i++){
                int j = i + diff;
                String substr = s.substring(i, j+1);
                if (diff <= 3){
                    dp[i][j] = substr;
                }else{
                    dp[i][j] = substr;
                     // Loop for trying all results that we get after dividing the strings into 2 and combine the   results of 2 substrings
                    for (int k = i; k < j; k++){
                        if ((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }
                    // Loop for checking if string can itself found some pattern in it which could be repeated.
                    int subLen = substr.length();
                    for (int k = 0; k < subLen; k++){
                        String strK = substr.substring(0, k+1);
                        int kLen = strK.length();
                        if (subLen % kLen == 0 && substr.replaceAll(strK, "").length() == 0){
                            String str = subLen/kLen + "[" + dp[i][i+k] + "]";//note that we need use dp[i][i+k] instead of strK
                            if (str.length() < dp[i][j].length()){
                                dp[i][j] = str;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
