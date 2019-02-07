You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.

class Solution {
    public int arrangeCoins(int n) {
        long start = 1;
        long end = n;
        while (start + 1 < end){
            long mid = start + (end - start) / 2;
            long candid = mid * (mid+1) / 2;
            if (candid <= n){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (end*(end+1) / 2 <= n){
            return (int) end;
        }
        return (int) start;
    }
}


Better:
class Solution {
    public int arrangeCoins(int n) {
        long start = 1;
        long end = n;
        while (start + 1 < end){
            long mid = start + (end - start) / 2;
            long res = (1 + mid) * mid / 2;
            if (res == n){
                return (int)mid;
            }else if (res < n){
                start = mid;
            }else{
                end = mid;
            }
        }
        if ((start+1)*start/2 <= n){
            return (int)start;
        }
        return (int)end;
    }
}
