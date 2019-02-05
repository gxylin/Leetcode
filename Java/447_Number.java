Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such 
that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in 
the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++){
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++){
                if (i == j){
                    continue;
                }
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int distance = dx * dx + dy * dy;
                map.put(distance, map.getOrDefault(distance, 0) + 1);
                
            }
            for (int val : map.values()){
                count += val * (val - 1); //permutation
            }
        }
        return count;
    }
}


class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int[] p : points){
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] q : points){
                if (p[0] == q[0] && p[1] == q[1]){
                    continue;
                }
                int dist = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (int val : map.values()){
                count += val * (val - 1);
            }
        }
        return count;
    }
}
