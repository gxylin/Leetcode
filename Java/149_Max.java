Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6

https://leetcode.com/problems/max-points-on-a-line/discuss/47113/A-java-solution-with-notes
/*
     *  A line is determined by two factors,say y=ax+b
     *  
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course). 

     *  Consider the gap between two points.

     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant

     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b

     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a

     *  So we can use y0&x0 to track a line;
     */
     

Best Solution:
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0){
            return 0;
        }
        int res  = 0;
        for (int i = 0; i < points.length; i++){
            Map<String, Integer> map = new HashMap<>();
            int dup = 0;
            int max = 0;
            for (int j = i + 1; j < points.length; j++){
                Point p1 = points[i];
                Point p2 = points[j];
                if (p1.x == p2.x && p1.y == p2.y){
                    dup++;
                    continue;
                }
                int deltaX = p1.x - p2.x;
                int deltaY = p1.y - p2.y;
                int gcd = gcd(deltaX, deltaY);
                int dx = deltaX / gcd;
                int dy = deltaY / gcd;
                String key = dx + ":" + dy;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + dup + 1);//+1 means to include itself
        }
        return res;
    }
    private int gcd (int a, int b){
        if (b == 0){
            return a;
        }
        return gcd (b, a%b);
    }
}
