Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true

Example 2:

Input: [[1,1],[-1,-1]]
Output: false

Follow up:
Could you do better than O(n2) ?

Note that (min + max) / 2 == (left + right) / 2 == > right = sum - left

class Solution {
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] point : points){
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            set.add(point[0] + "_" + point[1]);
        }
        int sum = max + min;
        for (int[] point : points){
            if (!set.contains((sum - point[0]) + "_" + point[1])){
                return false;
            }
        }
        return true;
    }
}
