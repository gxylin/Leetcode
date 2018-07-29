Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.

Method 1:
class Solution {
    public boolean hasAlternatingBits(int n) {
        int prev = 2;
        while (n != 0){
            int curr = n % 2;
            n /= 2;
            if (prev != 2 && (curr ^ prev) == 0){
                return false;
            }
            prev = curr;
        }
        return true;
    }
}

class Solution {
    public boolean hasAlternatingBits(int n) {
        int cur = n % 2;
        n /= 2;
        while (n > 0) {
            if (cur == n % 2) return false;
            cur = n % 2;
            n /= 2;
        }
        return true;
    }
}

Method 2:
class Solution {
    public boolean hasAlternatingBits(int n) {
        n = (n >> 1) ^ n;
        return (n & (n+1)) == 0;
    }
}

class Solution {
    public boolean hasAlternatingBits(int n) {
        return ((n ^= n/2) & n+1) == 0;
    }
}


Method 3:
class Solution {
    public boolean hasAlternatingBits(int n) {
        String bits = Integer.toBinaryString(n);
        for (int i = 0; i < bits.length() - 1; i++){
            if (bits.charAt(i) == bits.charAt(i+1)){
                return false;
            }
        }
        return true;
    }
}
