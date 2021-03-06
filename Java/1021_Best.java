Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.

 

Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 

Note:

2 <= A.length <= 50000
1 <= A[i] <= 1000

Method 1:
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++){
            while (!stack.isEmpty() &&  A[i] - i > A[stack.peek()] - stack.peek()){
                int ind = stack.pop();
                max = Math.max(max, A[i] + A[ind] + ind - i);
            }
            stack.push(i);
        }
        while (stack.size() > 1){
            int curr = stack.pop();
            int next = stack.peek();
            max = Math.max(max, A[curr] + A[next] + next - curr);
        }
        return max;
    }
}

Method 2:
https://leetcode.com/problems/best-sightseeing-pair/discuss/260850/JavaC%2B%2BPython-One-Pass
Time complexity: O(N)
Space complexity: O(1)
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int max = Integer.MIN_VALUE;
        int curr = 0;
        for (int i : A){
            max = Math.max(max, curr + i);
            curr = Math.max(curr, i) - 1;
        }
        return max;
    }
}
