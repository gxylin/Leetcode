Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points, with sides not 
necessarily parallel to the x and y axes.

If there isn't any rectangle, return 0.

Method 1: Brute force 
O(N^3)
class Solution {
    public double minAreaFreeRect(int[][] points) {
        Set<String> set = new HashSet<>();
        for (int[] p : points){
            set.add(p[0] + ":" + p[1]);
        }
        double res = Double.MAX_VALUE;
        for (int[] p1 : points){
            for (int[] p2 : points){
                if (p1[0] == p2[0] && p1[1] == p2[1]){
                    continue;
                }
                for (int[] p3 : points){
                    if (p1[0] == p3[0] && p1[1] == p3[1] || p2[0] == p3[0] && p2[1] == p3[1]){
                        continue;
                    }
                    //pythagorean theorem
                    if (dist(p1, p3) + dist(p2, p3) != dist(p1, p2)){
                        continue;
                    }
                    // x4 = x3 + (x2 - x1)
                    //y4 = y3 + (y2 - y1)
                    int x = p1[0] + p2[0] - p3[0];
                    int y = p1[1] + p2[1] - p3[1];
                    if (!set.contains(x + ":" + y)){
                        continue;
                    }
                    double area = Math.sqrt(dist(p1,p3)) * Math.sqrt(dist(p2, p3));
                    res = Math.min(res, area);
                }
            }
        }
        return Double.compare(Double.MAX_VALUE, res) == 0 ? 0 : res;
    }
    private int dist(int[] p1, int[] p2){
        int x = p1[0] - p2[0];
        int y = p1[1] - p2[1];
        return x*x + y*y;
    }
}
