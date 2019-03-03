A sequence X_1, X_2, ..., X_n is fibonacci-like if:

n >= 3
X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)

 

Example 1:

Input: [1,2,3,4,5,6,7,8]
Output: 5
Explanation:
The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: [1,3,7,11,12,14,18]
Output: 3
Explanation:
The longest subsequence that is fibonacci-like:
[1,11,12], [3,11,14] or [7,11,18].
 

Note:

3 <= A.length <= 1000
1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
(The time limit has been reduced by 50% for submissions in Java, C, and C++.)


very similar as Longest Increase subsequence, the only difference is that use pair (i, j) and (j, k) instead of single number to show 
(i,j) and (j, k) are connected, also hash (i,j) to one number in hashmap

Method: dp
Time complexity: O(N^2)
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < N; i++){
            index.put(A[i], i);
        }
        Map<Integer, Integer> dp = new HashMap<>();
        int res = 0;
        for (int k = 2; k < N; k++){
            for (int j = 0; j < k; j++){
                int i = index.getOrDefault(A[k] - A[j], -1); // show that (i, j) and (j, k) are connected
                if (i >= 0 && i < j){
                    int keyJ = i * N + j; //hash pair
                    int keyK = j * N + k;
                    int valJ = dp.getOrDefault(keyJ, 2);
                    int valK = dp.getOrDefault(keyK, 3);
                    if (valJ + 1 >= valK){
                        dp.put(keyK, valJ + 1);
                        res = Math.max(res, valJ + 1);
                    }
                }
            }
        }
        return res >= 3 ? res : 0;
    }
}

class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap<>();
        int[][] dp = new int[N][N];
        int res = 0;
        for (int k = 0; k < N; k++){
            index.put(A[k], k);
            for (int j = 0; j < k; j++){
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j){
                    dp[j][k] = Math.max(dp[i][j] + 1, 3);
                    res = Math.max(res, dp[j][k]);
                }
            }
        }
        return res >= 3 ? res : 0;
    }
}


Best:
dp[i][j] represents the length of longest sequence which ends with A[i] and A[j].
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        int[][] dp = new int[N][N];
        Map<Integer, Integer> pos = new HashMap<>(); // to reduce one dimension loop
        for (int i = 0; i < N; i++){
            Arrays.fill(dp[i], 2);
            pos.put(A[i], i);
        }
        int max = 2;
        for (int j = 0; j < N; j++){
            for (int i = 0; i < j; i++){
                int first = A[j] - A[i];
                if (first >= A[i]){
                    continue;
                }
                if (!pos.containsKey(first)){
                    continue;
                }
                int index = pos.get(first);
                dp[i][j] = dp[index][i] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max == 2 ? 0 : max;
    }
}
