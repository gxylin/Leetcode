Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.

Method 1: backtracking
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), S, 0);
        return res;
    }
    private void dfs(List<Integer> res, List<Integer> list, String S, int start){
        if (!res.isEmpty()){
            return;
        }
        if (start == S.length() && list.size() >= 3){
            for (int i : list){
                res.add(i);
            }
            return;
        }
        for (int i = start; i < S.length(); i++){
            if (S.charAt(start) == '0' && i > start){//no leading 0
                break;
            }
            long num = Long.parseLong(S.substring(start, i+1));
            if (num > Integer.MAX_VALUE){//must be signed integer
                break;
            }
            int size = list.size();
            if (size >= 2 && num > list.get(size - 2) + list.get(size - 1)){//early termination
                break;
            }
            if (size < 2 || num == list.get(size - 2) + list.get(size - 1)){
                list.add((int)num);
                dfs(res, list, S, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
