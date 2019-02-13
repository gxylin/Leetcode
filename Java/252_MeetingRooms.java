Given an array of meeting time intervals consisting of start and end times 
[[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false. 

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
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0){
            return true;
        }
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        for (int i = 1; i < intervals.length; i++){
            if (intervals[i].start < intervals[i-1].end){
                return false;
            }
        }
        return true;
    }
}

Method 2:
Sweep Line O(nlogn), n is the number of points
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
    class Point{
        int time;
        int flag;
        public Point(int time, int flag){
            this.time = time;
            this.flag = flag;
        }
    }
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0){
            return true;
        }
        List<Point> list = new ArrayList<>();
        for (Interval i : intervals){
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }
        Collections.sort(list, new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.time == b.time){
                    return a.flag - b.flag;
                }
                return a.time - b.time;
            }
        });
        int count = 0;
        for (Point p : list){
            if (p.flag == 1){
                count++;
            }else{
                count--;
            }
            if (count > 1){
                return false;
            }
        }
        return true;
    }
}


Method 3: the same as Meeting rooms II
Time complexity: O(nlogn)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0){
            return 0;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(Interval interval : intervals){
            map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
            map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
        }
        int active = 0;
        for (int val : map.values()){
            active += val;
            if (active > 1){
                return false;
            }
        }
        return true;
    }
}
