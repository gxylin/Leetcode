You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.

Method: use priority queue similar as Merge K array
class Solution {
    class Pair {
        int val;
        int list;
        int idx;
        public Pair (int val, int list, int idx){
            this.val = val;
            this.list = list;
            this.idx = idx;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                if (p1.val == p2.val){
                    return p1.idx - p2.idx;
                }
                return p1.val - p2.val;
            }
        });
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;//need keep tracking the max when enqueue in order to calculate the min window
        for (int i = 0; i < nums.size(); i++){
            pq.offer(new Pair(nums.get(i).get(0), i, 0));
            max = Math.max(max, nums.get(i).get(0));
        }
        while (pq.size() == nums.size()){
            Pair p = pq.poll();
            if (min > max - p.val){
                min = max - p.val;
                res[0] = p.val;
                res[1] = max;
            }
            List<Integer> l = nums.get(p.list);
            int index = p.idx + 1;
            if (index == l.size()){
                break;
            }
            max = Math.max(max, l.get(index));
            pq.offer(new Pair(l.get(index), p.list, index));
        }
        return res;   
    }
}


Better solution:
class Solution {
    class Point{
        int val;
        int index;
        int listIdx;
        public Point (int val, int index, int listIdx){
            this.val = val;
            this.index = index;
            this.listIdx = listIdx;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        Queue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>(){
            public int compare (Point p1, Point p2){
                if (p1.val == p2.val){
                    return p1.index - p2.index;
                }
                return p1.val - p2.val;
            }
        });
        int minLen = Integer.MAX_VALUE;//track the minimum length
        int maxVal = Integer.MIN_VALUE;//track the maximum value
        for (int i = 0; i < nums.size(); i++){
            List<Integer> list = nums.get(i);
            int val = list.get(0);
            pq.offer(new Point(val, 0, i));
            maxVal = Math.max(maxVal, val);
        }
        int[] res = new int[2];
        while (!pq.isEmpty()){
            Point p = pq.poll();
            if (minLen > maxVal - p.val){
                minLen = maxVal - p.val;
                res[0] = p.val;
                res[1] = maxVal;
            }
            if (p.index + 1 < nums.get(p.listIdx).size()){
                int nextVal = nums.get(p.listIdx).get(p.index + 1);
                pq.offer(new Point(nextVal, p.index + 1, p.listIdx));
                maxVal = Math.max(maxVal, nextVal);
            }else{
                break;
            }
        }
        return res;
    }
}
