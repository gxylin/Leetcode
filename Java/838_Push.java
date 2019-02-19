There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Note:

0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'

Method 1:
class Solution {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        StringBuilder right = new StringBuilder();
        for (int i = 0; i < len; i++){
            if (dominoes.charAt(i) == '.'){
                if (i > 0 && right.charAt(i-1) == 'R'){
                    right.append('R');
                }else{
                    right.append('.');
                }
            }else{
                right.append(dominoes.charAt(i));
            }  
        }
        StringBuilder left = new StringBuilder();
        for (int i = 0; i < len; i++){
            if (dominoes.charAt(len - 1 - i) == '.'){
                if (i > 0  && left.charAt(i-1) == 'L'){
                    left.append('L');
                }else{
                    left.append('.');
                }
            }else{
                left.append(dominoes.charAt(len - 1 - i));
            }  
        }
        left = left.reverse();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 0; i < len; i++){
            if (left.charAt(i) == right.charAt(i) || left.charAt(i) == '.' || right.charAt(i) == '.'){
                sb.append(left.charAt(i) != '.' ? left.charAt(i) : right.charAt(i));
            }else{
                start = i;
                int count = 0;
                while (i < len && left.charAt(i) != right.charAt(i)){
                    count++;
                    i++;
                }
                if (count % 2 == 0){
                    for (int j = start; j < i; j++){
                        if (j - start < count / 2){
                            sb.append('R');
                        }else{
                            sb.append('L');
                        }
                    }
                }else{
                    for (int j = start; j < i; j++){
                        if (j - start < count / 2){
                            sb.append('R');
                        }else if (j - start == count / 2){
                            sb.append('.');
                        }else{
                            sb.append('L');
                        }
                    }
                }
                i = start + count - 1;
            }
        }
        return sb.toString();
    }
}

Method 2: Best solution:
https://leetcode.com/problems/push-dominoes/discuss/132482/Java-one-pass-in-place-13ms

Two pointers:
key: 
1. four cases: L .... L
               R .... L
               L .... R
               R .... R
2. Record last seen L and R to find out the prevous letter
3. Pay attention to corner case at the start (prevL = -1, prevR = -1) and at the end (i == n)
class Solution {
    public String pushDominoes(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int n = dominoes.length();
        int prevL = -1; //previous L position
        int prevR = -1;
        for (int i = 0; i <= n; i++){
            if (i < n && arr[i] == 'L'){
                if (prevL > prevR || prevL == -1 && prevR == -1){//L....L
                    while (prevL < i){
                        prevL++;
                        arr[prevL] = 'L';
                    }
                }else{//R...L
                    int low = prevR + 1;
                    int high = i - 1;
                    while (low < high){
                        arr[low] = 'R';
                        arr[high] = 'L';
                        low++;
                        high--;
                    }
                    prevL = i;
                }
            }else if (i == n || arr[i] == 'R'){
                if (prevR > prevL){//R...R
                    while (prevR < i){
                       arr[prevR] = 'R';
                       prevR++; 
                    }
                }else{//L...R
                    prevR = i;
                }
            }
        }
        return new String(arr);
    }
}
