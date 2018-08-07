Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

Method 1: Use TreeSet for binary search
HashMap may not be very good since the problem is asking for the rectangular area no larger than K... 
From map we could only get the exact value of (val - target).
Not like 560	Subarray Sum Equals K.java, which could use HashMap

Time complexity: O(min(row, col)^2 * max(row, col)*log(max(row, col)))
Space complexity: O(max(row, col)
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int m = Math.min(row, col);
        int n = Math.max(row, col);
        boolean colLarge = col > row;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++){
            int[] arr = new int[n];
            for (int j = i; j >= 0; j--){
                int preSum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);// 0 means the sum equals k
                for (int c = 0; c < n; c++){
                    arr[c] = arr[c] + (colLarge == true ? matrix[j][c] : matrix[c][j]);
                    preSum += arr[c];
                    Integer Int = set.ceiling(preSum - k);
                    if (Int != null){
                        res = Math.max(res, preSum - Int);
                    }
                    set.add(preSum);
                }
            }
        }
        return res;
    }
}
