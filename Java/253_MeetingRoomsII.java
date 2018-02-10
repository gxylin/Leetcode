Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2. 

Method 1:
This is a two pointer problem (greedy solution).
Sort start and end intervals. Take two pointers, one for start time and one for end time. 
if the start interval is less than the end interval increment the room counter 
since we would need a extra room, else decrement the count since we have freed up the room.
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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0){
            return 0;
        }
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++){
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int startPtr = 0;
        int endPtr = 0;
        int count = 0;
        int max = 0;
        while(startPtr < len){
            if (starts[startPtr] < ends[endPtr]){
                count++;
                startPtr++;
            }else{
                count--;
                endPtr++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
