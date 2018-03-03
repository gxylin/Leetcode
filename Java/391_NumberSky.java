Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

 Notice
If landing and flying happens at the same time, we consider landing should happen at first.

Example
For interval list

[
  [1,10],
  [2,3],
  [5,8],
  [4,7]
]
Return 3


Method: sweep line O(nlogn), n is the number of points
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
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    class Point{
        int time;
        int flag;
        public Point(int time, int flag){
            this.time = time;
            this.flag = flag;
        }
    }
    public int countOfAirplanes(List<Interval> airplanes) {
        List<Point> pointList = new ArrayList<>();
        for (Interval i : airplanes){
            pointList.add(new Point(i.start, 1));
            pointList.add(new Point(i.end, 0));
        }
        Collections.sort(pointList, new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.time == b.time){
                    return a.flag - b.flag;
                }
                return a.time - b.time;
            }
        });
        int count = 0;
        int max = 0;
        for (Point p : pointList){
            if (p.flag == 1){
                count++;
            }else{
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
