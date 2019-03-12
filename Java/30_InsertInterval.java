Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Have you met this question in a real interview? Yes
Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

Method 1: based on 156. Merge Interval
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals == null ||intervals.size() == 0){
            result.add(newInterval);
            return result;
        }
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start){
            idx++;
        }
        intervals.add(idx, newInterval);
        
        //the following code is the same as 156 Merge Interval
        Interval last = null;
        for (Interval item : intervals){
            if (last == null || last.end < item.start){
                result.add(item);
                last = item;
            }else{
                last.end = Math.max(last.end, item.end);
            }
        }
        return result;
    }
}

Method 2:
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param intervals: Sorted interval list.
     * @param newInterval: new interval.
     * @return: A new interval list.
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0){
            result.add(newInterval);
            return result;
        }
        int idx = 0;
        int size = intervals.size();
        while (idx < size && intervals.get(idx).end < newInterval.start){
            result.add(intervals.get(idx));
            idx++;
        }

        while (idx < size && intervals.get(idx).start <= newInterval.end){
            newInterval.start = Math.min(newInterval.start, 
                        intervals.get(idx).start); 
            newInterval.end = Math.max(newInterval.end, intervals.get(idx).end); 
            idx++;
        }

        result.add(newInterval);
        while (idx < size){
            result.add(intervals.get(idx));
            idx++;
        }
        return result;
    }
}


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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        intervals.add(newInterval);
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare (Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        Interval last = null;
        for (Interval interval : intervals){
            if (last == null || last.end < interval.start){
                last = interval;
                res.add(interval);
            }else{
                last.end = Math.max(last.end, interval.end);
            }
        }
        return res;
    }
}


Best solution:
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null){
            res.add(newInterval);
            return res;
        }
        int idx = 0;
        while (idx < intervals.size() && intervals.get(idx).start < newInterval.start){
            idx++;
        }
        intervals.add(idx, newInterval);
        Interval last = null;
        for (int i = 0; i < intervals.size(); i++){
            if (last == null || last.end < intervals.get(i).start){
                res.add(intervals.get(i));
                last = intervals.get(i);    
            }else{
                last.end = Math.max(last.end, intervals.get(i).end);
            }
        }
        return res;
    }
}
