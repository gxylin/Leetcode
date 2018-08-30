 We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

    schedule and schedule[i] are lists with lengths in range [1, 50].
    0 <= schedule[i].start < schedule[i].end <= 10^8.

Method: Similar as Merge Interval

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
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<>();
        for (List<Interval> ls : schedule){
            for (Interval i : ls){
                list.add(i);
            }
        }
        //merge interval
        Collections.sort(list, new Comparator<Interval>(){
            public int compare (Interval a, Interval b){
                return a.start - b.start;
            }
        });
        List<Interval> mergeList = new ArrayList<>(); 
        Interval last = null;
        for (Interval item : list){
            if (last == null || last.end < item.start){
                mergeList.add(item);
                last = item;
            }else{
                last.end = Math.max(last.end, item.end);
            }
        }
        ///
        List<Interval> res = new ArrayList<>();
        for (int i = 0; i < mergeList.size() - 1; i++){
            int start = mergeList.get(i).end;
            int end = mergeList.get(i+1).start;
            res.add(new Interval(start, end));
        }
        return res;
    }
}
