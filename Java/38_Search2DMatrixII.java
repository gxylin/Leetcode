Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.
Have you met this question in a real interview? Yes
Example
Consider the following matrix:

[
  [1, 3, 5, 7],
  [2, 4, 7, 8],
  [3, 5, 9, 10]
]
Given target = 3, return 2.

Challenge 
O(m+n) time and O(1) extra space


• 左下角这个元素非常特殊(记为x)
– 如果target < x 最后的一行还有继续比较的意义吗?
– 如果target > x 最左的一列还有继续比较的意义吗?
– 如果target = x最后一行 & 最左的一列还有继续比较的意义吗?

• 所以算法是:
– target < x 砍掉最后一行
– target > x 砍掉最左一列
– target = x 计数+1,砍掉最后一行&砍掉最左一列

• 时间复杂度O(n+m)
– 盯住哪个元素会成为左下角元素x,x每次向上or向右or右上走一步 – 在矩阵中最多只能走n+m步

public class Solution {
    /*
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        int r = matrix.length - 1;
        int c = 0;
        int count = 0;
        while (r >= 0 && c < matrix[0].length){
            if (matrix[r][c] == target){
                r--;
                c++;
                count++;
            }else if (matrix[r][c] < target){
                c++;
            }else{
                r--;
            }
        }
        return count;
    }
}
