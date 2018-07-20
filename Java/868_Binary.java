Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.

If there aren't two consecutive 1's, return 0.

 

Example 1:

Input: 22
Output: 2
Explanation: 
22 in binary is 0b10110.
In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
The first consecutive pair of 1's have distance 2.
The second consecutive pair of 1's have distance 1.
The answer is the largest of these two distances, which is 2.
Example 2:

Input: 5
Output: 2
Explanation: 
5 in binary is 0b101.
Example 3:

Input: 6
Output: 1
Explanation: 
6 in binary is 0b110.
Example 4:

Input: 8
Output: 0
Explanation: 
8 in binary is 0b1000.
There aren't any consecutive pairs of 1's in the binary representation of 8, so we return 0.
 

Note:

1 <= N <= 10^9

Method 1:
Time Complexity: O(logN). Note that logN is the number of digits in the binary representation of N.
Space Complexity: O(logN), the space used by A. 
class Solution {
    public int binaryGap(int N) {
        int max = 0;
        List<Integer> list = new ArrayList<>();
        int index = 0;
        while (N > 0){
            if (N % 2 == 1){
                list.add(index);
            }
            N /= 2;
            index++;
        }
        for (int i = 1; i < list.size(); i++){
            max = Math.max(max, list.get(i) - list.get(i-1));
        }
        return max;
    }
}

Method 2:
Since we only care about consecutive values of this array A, we don't need to store the whole array.
We only need to remember the last value seen.
Time complexity: O(logN)
Space complexity: O(1)
class Solution {
    public int binaryGap(int N) {
        int max = 0;
        int index = 0;
        int lastIndex = -1;
        while (N > 0){
            if (N % 2 == 1){
                if (lastIndex >= 0){
                    max = Math.max(max, index - lastIndex);   
                }
                lastIndex = index;   
            }
            N /= 2;
            index++;
        }
        return max;
    }
}
