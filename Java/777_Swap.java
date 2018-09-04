In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Note:

1 <= len(start) = len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.

Method 1: Invariant
1. number of L should be equal to number of R
2. L can only shift to left; R can only shift to right
class Solution {
    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))){
            return false;
        }
        int t = 0;
        for (int i = 0; i < start.length(); i++){
            if (start.charAt(i) != 'L'){
                continue;
            }
            while (end.charAt(t) != 'L'){
                t++;
            }
            if (t > i){
                return false;
            }
            t++;
        }
        t = 0;
        for (int i = 0; i < start.length(); i++){
            if (start.charAt(i) != 'R'){
                continue;
            }
            while (end.charAt(t) != 'R'){
                t++;
            }
            if (t < i){
                return false;
            }
            t++;
        }
        return true;
    }
}

Method 2: One pass Better solution
class Solution {
    public boolean canTransform(String start, String end) {
        char[] S = start.toCharArray();
        char[] T = end.toCharArray();
        int i = 0;
        int j = 0;
        int N = S.length;
        while (i < N && j < N){
            while (i < N && S[i] == 'X'){
                i++;
            }
            while (j < N && T[j] == 'X'){
                j++;
            }
            if ((i == N) ^ (j == N)){
                return false;
            }
            if (i < N && j < N){
                 if (S[i] != T[j] || (S[i] == 'L' && i < j) || (S[i] == 'R' && i > j)){
                    return false;
                }
            }
            i++;
            j++;
        }
        return true;
    }
}
