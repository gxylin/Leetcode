Given a collection of intervals, merge all overlapping intervals.

Have you met this question in a real interview? Yes
Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]

• 区间左端点从小到大排个序,从左往右扫一遍 :
– 不能合并 ->直接下一个
– 能合并 -> 就合并

/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */


public class Solution {
    /*
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0){
            return result;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
           public int compare(Interval a, Interval b){
               return a.start - b.start;
           } 
        });
        
        Interval last = null;
        for(Interval item : intervals){
            if (last == null || last.end < item.start){
                result.add(item);
                last = item;
            }else{
                last.end = Math.max(last.end, item.end);  // Modify the element already in list
            }
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
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare (Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        Interval last = null;
        for (Interval interval : intervals){
            if (last == null || last.end < interval.start){
                last = interval;
                res.add(last);
            }else{
                last.end = Math.max(last.end, interval.end);
            }
        }
        return res;
    }
}
