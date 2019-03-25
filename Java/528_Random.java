Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.


https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search

class Solution {
    Random random;
    int[] wSum;
    public Solution(int[] w) {
        random = new Random();
        wSum = new int[w.length];
        wSum[0] = w[0];
        for (int i = 1; i < w.length; i++){
            wSum[i] = wSum[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int n = wSum.length;
        int max = wSum[n-1];
        int target = random.nextInt(max) + 1;
        //the below will be the same as Leetcode 35: search insert position
        int start = 0;
        int end = n-1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (wSum[mid] == target){
                return mid;
            }else if (wSum[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (wSum[start] >= target){
            return start;
        }else if (wSum[end] >= target){
            return end;
        }
        return n-1;
    }
}
