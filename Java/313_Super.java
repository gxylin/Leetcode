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
