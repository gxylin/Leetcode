Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)


Sweep line similar as meeting room

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
    class Pair {
        int val;
        int flag;
        public Pair (int val, int flag){
            this.val = val;
            this.flag = flag;
        }
    }
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> list = new ArrayList<>();
        List<Pair> pairs = new ArrayList<>();
        for (Interval i : A){
            pairs.add(new Pair(i.start, 1));
            pairs.add(new Pair(i.end, -1));
        }
        for (Interval i : B){
            pairs.add(new Pair(i.start, 1));
            pairs.add(new Pair(i.end, -1));
        }
        Collections.sort(pairs, new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                if (p1.val == p2.val){
                    return p2.flag - p1.flag; //start must go ahead
                }
                return p1.val - p2.val;
            }
        });
        int active = 0;
        int prev = Integer.MIN_VALUE;
        for (Pair p : pairs){
            if (p.flag == 1){
                active++;
            }else{
                active--;
            }
            if (active == 2){
                if (prev == Integer.MIN_VALUE){
                    prev = p.val;
                }
            }else if (active == 1){
                if (prev != Integer.MIN_VALUE){
                    list.add(new Interval(prev, p.val));
                    prev = Integer.MIN_VALUE;
                }
            }
        }
        Interval[] res = new Interval[list.size()];
        for (int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
