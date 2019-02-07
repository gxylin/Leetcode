Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or
equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start
point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i.
Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

Method 1: HashMap O(nlogn)
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] result = new int [intervals.length];
        Map<Integer, Integer> map = new HashMap<>();
        int[] startPts = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++){
            map.put(intervals[i].start, i);
            startPts[i] = intervals[i].start;
        }
        Arrays.sort(startPts);
        for (int i = 0; i < intervals.length; i++){
            int key = binarySearch(startPts, intervals[i].end); //binary search end point in sorted start points array
            if (key != Integer.MAX_VALUE){
                result[i] = map.get(key);
            }else{
                result[i] = -1;
            }
        }
        return result;
    }
    private int binarySearch(int[] startPts, int target){
        int left = 0;
        int right = startPts.length - 1;
        while (left + 1 < right){
            int mid = left + (right - left) / 2;
            int key = startPts[mid];
            if (key == target){
                return key;
            }else if (key > target){
                right = mid;
            }else{
                left = mid;
            }
        }
        if (startPts[left] >= target){
            return startPts[left];
        }else if (startPts[right] >= target){
            return startPts[right];
        }
        return Integer.MAX_VALUE; // return start point value not index
    }
}

Method 2: TreeMap O(nlogn)
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] result = new int [intervals.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++){
            treeMap.put(intervals[i].start, i);
        }
        for (int i = 0; i < intervals.length; i++){
            Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(intervals[i].end);
            if (entry != null){
                result[i] = entry.getValue();
            }else{
                result[i] = -1;
            }
        }
        return result;
    }
}

TreeMap comparator is implemented the same as others
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[] result = new int [intervals.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(new Comparator<Integer>(){
            public int compare (Integer a, Integer b){
                return a - b;
            }
        });
        for (int i = 0; i < intervals.length; i++){
            treeMap.put(intervals[i].start, i);
        }
        for (int i = 0; i < intervals.length; i++){
            Map.Entry<Integer, Integer> entry = treeMap.ceilingEntry(intervals[i].end);
            if (entry != null){
                result[i] = entry.getValue();
            }else{
                result[i] = -1;
            }
        }
        return result;
    }
}


Best solution:
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int n = intervals.length;
        int[] res = new int[n];
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        for (int i = 0; i < n; i++){
            treemap.put(intervals[i].start, i);
        }
        for (int i = 0; i < n; i++){
            Integer key = treemap.ceilingKey(intervals[i].end);
            if (key == null){
                res[i] = -1;
            }else{
                res[i] = treemap.get(key);
            }
        }
        return res;
    }
}
