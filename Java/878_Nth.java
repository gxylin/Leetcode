A positive integer is magical if it is divisible by either A or B.

Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: N = 1, A = 2, B = 3
Output: 2
Example 2:

Input: N = 4, A = 2, B = 3
Output: 6
Example 3:

Input: N = 5, A = 2, B = 4
Output: 10
Example 4:

Input: N = 3, A = 6, B = 4
Output: 8
 

Note:

1 <= N <= 10^9
2 <= A <= 40000
2 <= B <= 40000

Method 1: TLE
Time complexity: NlogN
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        int modulo  = (int) Math.pow(10, 9) + 7;
        Queue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i <= N; i++){
            long a = i * (long) A;
            long b = i * (long) B;
            if (set.add(a)){
                pq.offer(a);
            }
            if (set.add(b)){
                pq.offer(b);
            }
        }
        int count = 0;
        while (count < N - 1){
            pq.poll();
            count++;
        }
        return (int) (pq.peek() % modulo);
    }
}

Method 2: Binary Search
https://leetcode.com/problems/nth-magical-number/discuss/154613/C++JavaPython-Binary-Search
Time complexity: O(logN)
class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        //first calculate gcd and lcm
        long a = A;
        long b = B;
        while (b > 0){
            long tmp = a;
            a = b;
            b = tmp % a;
        }
        long gcd = a;
        long lcm = (A * B) / gcd;
        long low = Math.min(A, B);
        long high = N * low;
        while (low <= high){
            long mid = low + (high - low) / 2;
            long numOfMagicNum = mid / A + mid / B - mid / lcm; // method to calculate number of magic number
            if (numOfMagicNum >= N){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        long mod = (long)1e9 + 7;
        return (int)(low % mod);
    }
}
