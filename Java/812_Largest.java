You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
Explanation: 
The five points are show in the figure below. The red triangle is the largest.

https://leetcode.com/problems/largest-triangle-area/discuss/122711/C++JavaPython-Solution-with-Explanation-and-Prove
class Solution {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int i = 0; i < points.length; i++){
            for (int j = i+1; j < points.length; j++){
                for (int k = j+1; k < points.length; k++){
                    max = Math.max(max, area(points[i], points[j], points[k]));
                }
            }
        }
        return max;
    }
    private double area(int[] p, int[] q, int[] r){
        return 0.5 * Math.abs(p[0]*q[1] + q[0]*r[1] + r[0]*p[1] - p[0]*r[1] - r[0]*q[1] - q[0]*p[1]);
    } 
}
