Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel
to the x and y axes.

If there isn't any rectangle, return 0.

 

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 

Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.

Method 1: Brute Force
Time complexity: O(N^2)
Space complexity: O(N)
class Solution {
    public int minAreaRect(int[][] points) {
        int max = 40000;
        int res = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        int n = points.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]){
                    continue;
                }
                if (set.contains(points[i][0] * max + points[j][1]) && set.contains(points[j][0] * max + points[i][1])){
                    int area = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]);
                    res = Math.min(res, area);
                }
            }
            set.add(points[i][0] * max + points[i][1]);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

Method 2:
class Solution {
    public int minAreaRect(int[][] points) {
        int res = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points){
            if (!map.containsKey(p[0])){
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        for (int[] p1: points){
            for (int[] p2: points){
                if (p1[0] == p2[0] || p1[1] == p2[1]){ // note that this can't be &&
                    continue;
                }
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                    int area = Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
                    res = Math.min(res, area);
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
