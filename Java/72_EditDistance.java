Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 
1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Have you met this question in a real interview? Yes
Example
Given word1 = "mart" and word2 = "karma", return 3.


Let following be the function definition :-

f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.

f(i, j) = f(i - 1, j - 1)
Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper

f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
f(i, j - 1) represents insert operation
f(i - 1, j) represents delete operation
f(i - 1, j - 1) represents replace operation
Here, we consider any operation from word1 to word2. It means, when we say insert operation, 
we insert a new character after word1 that matches the jth character of word2. 
So, now have to match i characters of word1 to j - 1 characters of word2. Same goes for other 2 operations as well.

Note that the problem is symmetric. The insert operation in one direction (i.e. from word1 to word2) 
is same as delete operation in other. So, we could choose any direction.

Above equations become the recursive definitions for DP.

Base Case:

f(0, k) = f(k, 0) = k

public class Solution {
    /*
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] cost = new int[n+1][m+1];
        for (int i = 0; i <= n; i++){
            cost[i][0] = i;
        }
        for (int j = 0; j <= m; j++){
            cost[0][j] = j;
        }
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
            // note that cost[i][j] represents the comparison at the point between i - 1 of word1 and j - 1 of word2
                if (word1.charAt(i-1) == word2.charAt(j-1)){ 
                    cost[i][j] = cost[i-1][j-1];
                }else{
                    cost[i][j] = 1 + Math.min(cost[i-1][j-1],Math.min(cost[i][j-1], cost[i-1][j]));
                }
            }
        }
        return cost[n][m];
    }
}
