Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
Catalan number:
   https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
   https://www.geeksforgeeks.org/program-nth-catalan-number/

G(n): the number of unique BST for a sequence of length n.
F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.

F(i, n) = G(i-1) * G(n-i) 1<= i <= n
G(n) = F(1, n) + F(2, n) + ... + F(n, n)
     = G(0) * G(n-1) + G(1) * G(n-2) + ... + G(n-1) * G(0)
G(0) = 1, G(1) = 1

Iterative:
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 1; j <= i; j++){
                dp[i] += dp[j-1] * dp [i-j];
            }
        }
        return dp[n];
    }
}

Recursive: (TLE)
class Solution {
    public int numTrees(int n) {
        if (n <= 1){
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++){
            ans += numTrees(i-1) * numTrees(n-i);
        }
        return ans;
    }
}
+memorization => accepted
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int numTrees(int n) {
        if (map.containsKey(n)){
            return map.get(n);
        }
        if (n <= 1){
            map.put(n, 1);
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= n; i++){
            ans += numTrees(i-1) * numTrees(n-i);
        }
        map.put(n, ans);
        return ans;
    }
}
