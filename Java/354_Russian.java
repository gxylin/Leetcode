You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

The same as Longest Increase Subsequence
Method 1: Dynamic Programming
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare (int[] e1, int[] e2){
                if (e1[0] == e2[0]){
                    return e1[1] - e2[1];
                }
                return e1[0] - e2[0];
            }
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++){
            dp[i] = 1;
        }
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

Method 2: Binary Search
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare (int[] e1, int[] e2){
                if (e1[0] == e2[0]){
                    return e2[1] - e1[1];
                }
                return e1[0] - e2[0];
            }
        });
        int n = envelopes.length;
        int len = 0;
        int[] dp = new int[n];
        for (int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0){
                index = - (index+1);
            }
            dp[index] = envelope[1];
            if (index == len){
                len++;
            }
        }
        return len;
    }
}
