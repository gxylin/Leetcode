Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.


class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n >= 5){
            n = n/5;
            ans += n;
        }
        return ans;
    }
}

class Solution {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n/5 + trailingZeroes(n/5);
    }
}
