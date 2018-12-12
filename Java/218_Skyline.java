http://www.lintcode.com/en/problem/building-outline/
https://leetcode.com/problems/the-skyline-problem/description/


Sweep line + TreeSet

Leetcode:
class Solution {
    class Point{
        int height;
        int position;
        int flag;
        int start;
        public Point(int height, int position, int flag, int start){
            this.height = height;
            this.position = position;
            this.flag = flag;
            this.start = start;
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
         List<int[]> result = new ArrayList<>();
        List<Point> list = new ArrayList<>();
        for(int[] b : buildings){
            list.add(new Point(b[2], b[0], 1, b[0]));
            list.add(new Point(b[2], b[1], 0, b[0]));
        }
        Collections.sort(list, new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.position == b.position){
                    return b.flag - a.flag;
                }
                return a.position - b.position;
            }
        });
        TreeSet<Point> pointTree = new TreeSet<>(new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.height == b.height){
                    return a.position - b.position;
                }
                return a.height - b.height;
            }
        });
        int prevHeight = 0;
        int prevPos = -1;
        int currHeight = 0;
        
        for (Point p : list){
            if (p.flag == 1){
                pointTree.add(p);
                currHeight = pointTree.last().height;
            }else{
                pointTree.remove(new Point(p.height, p.start, 1, p.start));
                currHeight = pointTree.isEmpty() ? 0 : pointTree.last().height;
            }
            if (currHeight != prevHeight){
                if (prevHeight != 0 && prevPos != -1 && prevPos != p.position){
                    int[] item = new int[2];
                    item[0] = prevPos;
                    item[1] = prevHeight;
                    result.add(item);  
                }
                prevHeight = currHeight;
                prevPos = p.position;
                if (currHeight == 0){
                    int[] ground = new int[2];
                    ground[0] = p.position;
                    ground[1] = currHeight;
                    result.add(ground);
                }
            }
        }
        return result;
    }
}


Lintcode:
public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    class Point{
        int height;
        int position;
        int flag;
        int start;
        public Point(int height, int position, int flag, int start){
            this.height = height;
            this.position = position;
            this.flag = flag;
            this.start = start;
        }
    }
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<Point> list = new ArrayList<>();
        for(int[] b : buildings){
            list.add(new Point(b[2], b[0], 1, b[0]));
            list.add(new Point(b[2], b[1], 0, b[0]));
        }
        Collections.sort(list, new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.position == b.position){
                    return b.flag - a.flag;
                }
                return a.position - b.position;
            }
        });
        TreeSet<Point> pointTree = new TreeSet<>(new Comparator<Point>(){
            public int compare(Point a, Point b){
                if (a.height == b.height){
                    return a.position - b.position;
                }
                return a.height - b.height;
            }
        });
        int prevHeight = 0;
        int prevPos = 0;
        int currHeight = 0;
        
        for (Point p : list){
            if (p.flag == 1){
                pointTree.add(p);
                currHeight = pointTree.last().height;
            }else{
                pointTree.remove(new Point(p.height, p.start, 1, p.start));
                currHeight = pointTree.isEmpty() ? 0 : pointTree.last().height;
            }
            if (currHeight != prevHeight){
                if (prevHeight != 0 && prevPos != 0 && prevPos != p.position){
                    List<Integer> item = new ArrayList<>();
                    item.add(prevPos);
                    item.add(p.position);
                    item.add(prevHeight);
                    result.add(item);
                }
                prevHeight = currHeight;
                prevPos = p.position;
            }
        }
        return result;
    }
}

https://leetcode.com/problems/the-skyline-problem/discuss/61193/Short-Java-solution
Best solution:
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b : buildings){
            height.add(new int[]{b[0], b[2]});
            height.add(new int[]{b[1], -b[2]});
        }
        Collections.sort(height, new Comparator<int[]>(){
           public int compare (int[] a, int[] b){
               if (a[0] == b[0]){
                   return b[1] - a[1];//start must go first
               }
               return a[0] - b[0];
           } 
        });
        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return (int)(i2 - i1);
            }
        });
        pq.offer(0);
        int prev = 0;
        for (int[] b : height){
            if (b[1] > 0){//start
                pq.offer(b[1]);
            }else{//end
                pq.remove(-b[1]);
            }
            int curr = pq.peek();
            if (prev != curr){
                res.add(new int[]{b[0], curr});
                prev = curr;
            }
        }
        return res;
    }
}
