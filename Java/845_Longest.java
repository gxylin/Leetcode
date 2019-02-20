Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?

Method 1:
class Solution {
    public int longestMountain(int[] A) {
        if (A.length < 3){
            return 0;
        }
        int longest = 0;
        int i = 0;
        while (i < A.length - 1){
            while (i < A.length - 1 && A[i+1] <= A[i]){
                i++;
            }
            int start = i;
            while (i < A.length - 1 && A[i+1] > A[i]){
                i++;
            }
            int peak = i;
            while (i < A.length - 1 && A[i+1] < A[i]){
                i++;
            }
            int end = i;
            if (start < peak && peak < end){
                longest = Math.max(longest, i - start + 1);
            }
        }
        return longest;
    }
}

class Solution {
    public int longestMountain(int[] A) {
        if (A.length < 3){
            return 0;
        }
        int max = 0;
        int i = 0;
        int j = 0;
        while (i < A.length){
            j = i+1;
            boolean up = false;
            boolean down = false;
            while (j < A.length && A[j] > A[j-1]){
                up = true;
                j++;
            }
            if (j == i+1){
                i++;
                continue;
            }
            while (j < A.length && A[j] < A[j-1]){
                down = true;
                j++;
            }
            if (up && down){
                max = Math.max(max, j - i);
                
            }
            i = j - 1;
        }
        return max;
    }
}

Method 2: Similar as 
Maximize Distance to Closest Person
Shortest Distance to a Character

Time compleixty: O(n)
Space complexity: O(n)
class Solution {
    public int longestMountain(int[] A) {
        int n = A.length;
        int[] increase = new int[n];
        int[] decrease = new int[n];
        for (int i = 1; i < n; i++){
            if (A[i] > A[i-1]){
                increase[i] = increase[i-1] + 1;
            }
        }
        for (int i = n-2; i >= 0; i--){
            if (A[i] > A[i+1]){
                decrease[i] = decrease[i+1] + 1;
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            if (increase[i] > 0 && decrease[i] > 0){
                max = Math.max(max, increase[i] + decrease[i] + 1);
            }
        }
        return max;
    }
}

class Solution {
    public int longestMountain(int[] A) {
        int n = A.length;
        int[] increase = new int[n];
        int[] decrease = new int[n];
        for (int i = 1; i < n; i++){
            if (A[i] > A[i-1]){
                increase[i] = increase[i-1] + 1;
            }
        }
        int max = 0;
        for (int i = n-2; i >= 0; i--){
            if (A[i] > A[i+1]){
                decrease[i] = decrease[i+1] + 1;
            }
            if (increase[i] > 0 && decrease[i] > 0){
                max = Math.max(max, increase[i] + decrease[i] + 1);
            }
        }
        return max;
    }
}

Best solution
Time complexity: O(n)
Space complexiyt: O(1)
class Solution {
    public int longestMountain(int[] A) {
        int max = 0;
        int up = 0;
        int down = 0;
        for (int i = 1; i < A.length; i++){
            if (down > 0 && A[i] > A[i-1] || A[i] == A[i-1]){
                down = 0;
                up = 0;
            }
            if (A[i] > A[i-1]){
                up++;
            }
            if (A[i] < A[i-1]){
                down++;
            }
            if (up > 0 && down > 0){
                max = Math.max(max, up + down + 1);
            }
        }
        return max;
    }
}
