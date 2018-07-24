Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

Example:

Input: n = 12, primes = [2,7,13,19]
Output: 32 
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
             super ugly numbers given primes = [2,7,13,19] of size 4.
Note:

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.

Method 1: O(nlogn + onlogk) TLE
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Queue<Integer> pq = new PriorityQueue<Integer>();
        Set<Integer> seen = new HashSet<>();
        pq.offer(1);
        seen.add(1);
        int v = 1;
        for (int i = 1; i <= n; i++){
            v = pq.poll();
            for (int j = 0; j < primes.length; j++){
                int cand = v * primes[j];
                if (!seen.contains(cand)){
                    pq.offer(cand);
                    seen.add(cand);
                }
            }
        }
        return v;
    }
}

Method 2: O(nk) the principle is like merge sort
https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
https://leetcode.com/problems/super-ugly-number/discuss/76291/Java-three-methods-23ms-36-ms-58ms(with-heap)-performance-explained
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] indices = new int[primes.length];
        for (int i = 1; i < n; i++){
            res[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++){
                res[i] = Math.min(res[i], primes[j] * res[indices[j]]);
            }
            for (int j = 0; j < primes.length; j++){
                if (res[i] == primes[j] * res[indices[j]]){
                    indices[j]++;
                }
            }
        }
        return res[n-1];
    }
}


