The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.


class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1<<n ; i++){
            result.add(i ^ i>>1);
        }
        return result;
    }
}

Better version:
backtrack
https://leetcode.com/problems/gray-code/discuss/29880/Backtracking-C++-solution
https://www.tutorialspoint.com/java/java_bitset_class.htm
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        backtrack(res, n, new BitSet());
        return res;
    }
    private void backtrack(List<Integer> res, int n, BitSet chosen){
        if (n == 0){
            res.add(convert(chosen));
            return;
        }
        backtrack(res, n-1, chosen);
        chosen.flip(n-1);
        backtrack(res, n-1, chosen);
    }
    private int convert(BitSet bits){
        int res = 0;
        for (int i = 0; i < bits.length(); i++){
            res += bits.get(i) ? (1 << i) : 0;
        }
        return res;
    }
}
