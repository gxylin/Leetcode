Given an array of unique integers, each integer is strictly greater than 1.

We make a binary tree using these integers and each number may be used for any number of times.

Each non-leaf node's value should be equal to the product of the values of it's children.

How many binary trees can we make?  Return the answer modulo 10 ** 9 + 7.

Example 1:

Input: A = [2, 4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]
Example 2:

Input: A = [2, 4, 5, 10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 

Note:

1 <= A.length <= 1000.
2 <= A[i] <= 10 ^ 9.


DP:
Sort the list A at first.
DP equation:

dp[i] = sum(dp[j] * dp[i / j])
res = sum(dp[i])
with i, j, i / j in the list L


Time complexity: O(N^2)
Space complexity: O(N)
class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        long mod = (long) Math.pow(10, 9) + 7;
        Arrays.sort(A);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < A.length; i++){
            index.put(A[i], i);
        }
        long[] dp = new long[A.length];
        for (int i = 0; i < A.length; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (A[i] % A[j] == 0 && index.containsKey(A[i] / A[j])){
                    dp[i] += dp[j] * dp[index.get(A[i] / A[j])];
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < A.length; i++){
            sum += dp[i];
        }
        return (int) (sum % mod);
    }
}

class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        long mod = (long) Math.pow(10, 9) + 7;
        Arrays.sort(A);
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < A.length; i++){
            index.put(A[i], i);
        }
        long[] dp = new long[A.length];
        for (int i = 0; i < A.length; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (A[i] % A[j] == 0 && index.containsKey(A[i] / A[j])){
                    dp[i] = (dp[i] + dp[j] * dp[index.get(A[i] / A[j])]) % mod;
                }
            }
        }
        long sum = 0;
        for (int i = 0; i < A.length; i++){
            sum += dp[i];
        }
        return (int) (sum % mod);
    }
}

Best solution:
class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        int mod = (int) Math.pow(10, 9) + 7;
        int n = A.length;
        Arrays.sort(A);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            map.put(A[i], i);
        }
        long[] dp = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++){
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (A[i] % A[j] == 0 && map.containsKey( A[i] / A[j] )){
                    int ind = map.get(A[i]/A[j]);
                    dp[i] = (dp[i] + (dp[j] * dp[ind]) % mod)%mod;
                }
            }
            sum = (sum + dp[i]) % mod;
        }
        return (int)sum;
    }
}

Use one hashmap
class Solution {
    public int numFactoredBinaryTrees(int[] A) {
        long mod = (long) Math.pow(10, 9) + 7;
        Arrays.sort(A);
        Map<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; i++){
            dp.put(A[i], 1L);
            for (int j = 0; j < i; j++){
                if (A[i] % A[j] == 0 && dp.containsKey(A[i] / A[j])){
                    dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.get(A[i] / A[j])) % mod);
                }
            }
        }
        long sum = 0;
        for (long val : dp.values()){
            sum += val;
        }
        return (int) (sum % mod);
    }
}


The largest value v used must be the root node in the tree. Say dp(v) is the number of ways to make a tree with root node v.

If the root node of the tree (with value v) has children with values x and y (and x * y == v), then there are dp(x) * dp(y) ways to make this tree.

In total, there are ∑x∗y=vdp(x)∗dp(y) ways to make a tree with root node v.

Algorithm

Actually, let dp[i] be the number of ways to have a root node with value A[i].

Since in the above example we always have x < v and y < v, we can calculate the values of dp[i] in increasing order using dynamic programming.

For some root value A[i], let's try to find candidates for the children with values A[j] and A[i] / A[j] (so that evidently A[j] * (A[i] / A[j]) = A[i]). To do this quickly, we will need index which looks up this value: if A[k] = A[i] / A[j], then index[A[i] / A[j]] = k`.

After, we'll add all possible dp[j] * dp[k] (with j < i, k < i) to our answer dp[i]. In our Java implementation, we carefully used long so avoid overflow issues.


