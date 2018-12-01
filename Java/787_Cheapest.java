There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

Similar to Network Delay time but include additional dimension (record how many stops)
Dijkstra algorithm

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //build graph
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights){
            Map<Integer, Integer> sourceMap = graph.get(flight[0]);
            if (sourceMap == null){
                sourceMap = new HashMap<>();
                graph.put(flight[0], sourceMap);
            }
            Integer d = sourceMap.get(flight[1]);
            if (d == null){
                sourceMap.put(flight[1], flight[2]);
            }
        }
        //minPQ to store the shortest distance from source to the current point
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare (int[] a, int[] b){
                return a[1] - b[1];
            }
        });
        Map<Integer, Integer> distMap = new HashMap<>(); // store the shortest distance between src and current point with different stops
   //     distMap.put(src, 0);
        pq.offer(new int[]{src, 0, 0});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int dist = cur[1];
            int stop = cur[2];
            if (stop > K + 1 || distMap.getOrDefault(stop * 1000 + node, Integer.MAX_VALUE) < dist){
                continue;
            }
            if (node == dst){
                return dist;
            }
            Map<Integer, Integer> sourceMap = graph.get(node);
            if (sourceMap == null){
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : sourceMap.entrySet()){
                int absoluteDist = dist + entry.getValue();
                int next = entry.getKey();
                int key = (stop+1) * 1000 + next;
                if (!distMap.containsKey(key) || distMap.get(key) > absoluteDist){
                    distMap.put(key, absoluteDist);
                    pq.offer(new int[]{next, absoluteDist, stop+1});
                }
            }
        }
        return -1;
    }
}


Better version:
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for(int[] flight : flights){
            if (!graph.containsKey(flight[0])){
                graph.put(flight[0], new HashMap<>());
            }
            graph.get(flight[0]).put(flight[1], flight[2]);
        }
        Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare (int[] f1, int[] f2){
                return f1[1] - f2[1];
            }
        });
        Map<Integer, Integer> distMap = new HashMap<>();
        distMap.put(src * 200, 0);
        pq.offer(new int[]{src, 0, -1});
        while (!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            int stop = curr[2];
            if (node == dst && stop <= K){
                return dist;
            }
            if (!graph.containsKey(node)){
                continue;
            }
            Map<Integer, Integer> map = graph.get(node);
            for (int nei : map.keySet()){
                int key = nei * 200 + (stop + 1); 
                if (!distMap.containsKey(key) || dist + map.get(nei) < distMap.get(key) && stop + 1 <= K){
                    distMap.put(key, dist + map.get(nei));
                    pq.offer(new int[]{nei, dist + map.get(nei), stop + 1});
                }
            }
        }
        return -1;
    }
}
