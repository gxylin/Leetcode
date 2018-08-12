Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

Note: 1 ≤ k ≤ n ≤ 109.

Example:

Input:
n: 13   k: 2

Output:
10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

    
    
https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/discuss/92242/ConciseEasy-to-understand-Java-5ms-solution-with-Explaination

class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k -1;
        while (k > 0){
            int steps = calStep(n, curr, curr+1);
            if (steps <= k){
                curr += 1;
                k -= steps;
            }else{
                curr *= 10;
                k--;
            }
        }
        return curr;
    }
    private int calStep(int n, long n1, long n2){
        int steps = 0;
        while (n1 <= n){
            steps += Math.min(n2 - n1, n - n1 + 1);
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }
}
