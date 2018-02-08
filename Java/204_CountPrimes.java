Description:

Count the number of prime numbers less than a non-negative number, n.

class Solution {
    public int countPrimes(int n) {
        int ans = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; i++){
            if (!notPrime[i]){
                ans++;
                for (int j = 2; i * j < n; j++){
                    notPrime[i*j] = true;
                }
            }
        }
        return ans;
    }
}
