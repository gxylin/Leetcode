Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.

Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < S.length(); i++){
            if (S.charAt(i) == C){
                queue.offer(i);
            }
        }
        int curr = -S.length();
        int next = queue.poll();
        for (int i = 0; i < S.length(); i++){
            if (i > next){
                curr = next;
                if (!queue.isEmpty()){
                    next = queue.poll();
                }else{
                    next = 2 * S.length();
                }
            }
            res[i] = Math.min(Math.abs(i-curr), Math.abs(next-i));
        }
        return res;
    }
}

Method 2: Best solution 
Similar question: https://github.com/optimisea/Leetcode/blob/master/Java/849_Maximize.java
Time complexity: O(n)
Space complexity: O(1)
Loop twice on the string S.
First forward pass to find shortest distant to character on left.
Second backward pass to find shortest distant to character on right.
class Solution {
    public int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] res = new int[n];
        int pos = -n;
        for (int i = 0; i < n; i++){
            if (S.charAt(i) == C){
                pos = i;
            }
            res[i] = i - pos;
        }
        pos = 2 * n;
        for (int i = n - 1; i >= 0; i--){
            if (S.charAt(i) == C){
                pos = i;
            }
            res[i] = Math.min(res[i], pos - i);
        }
        return res;
    }
}
