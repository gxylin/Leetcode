There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, 
and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

https://leetcode.com/problems/network-delay-time/discuss/109968/Simple-JAVA-Djikstra's-(PriorityQueue-optimized)-Solution-with-explanation
Dijkstra’s Algorithm finds the shortest distance from the start node to every other node in the graph
Dijkstra's algorithm is based on repeatedly making the candidate move that has the least distance travelled.

In our implementations below, we showcase both O(N^2) (basic) and O(NlogN) (heap) approaches.
  

class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time : times){
            Map<Integer, Integer> sourceMap = graph.get(time[0]);
            if (sourceMap == null){
                sourceMap = new HashMap<>();
                graph.put(time[0], sourceMap);
            }
            Integer val = sourceMap.get(time[1]);
            if (val == null || val > time[2]){
                sourceMap.put(time[1], time[2]);
            }
        }
         //Use PriorityQueue to get the node with shortest absolute distance 
        //and calculate the absolute distance of its neighbor nodes.
        
        Map<Integer, Integer> distanceMap = new HashMap<>();//store the minimal distance from K to this point
        distanceMap.put(K, 0);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
           public int compare (int[] a, int[] b){
               return a[1] - b[1];
           } 
        });
        pq.offer(new int[]{K, 0});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];
            Map<Integer, Integer> sourceMap = graph.get(node);
            if (sourceMap == null){
                continue;
            }
            for (Map.Entry<Integer, Integer> entry : sourceMap.entrySet()){
                int absoluteDist = distance + entry.getValue();
                int targetNode = entry.getKey();
                if (distanceMap.containsKey(targetNode) && distanceMap.get(targetNode) <= absoluteDist){
                    continue;
                }
                distanceMap.put(targetNode, absoluteDist);
                pq.offer(new int[]{targetNode, absoluteDist});
            }
        }
        int max = 0;
        for (int val : distanceMap.values()){
            max = Math.max(max, val);
        }
        return distanceMap.size() == N ? max : -1;
    }
}

Basic:
class Solution {
    Map<Integer, Integer> dist;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        dist.put(K, 0);
        boolean[] seen = new boolean[N+1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) break;
            seen[candNode] = true;
            if (graph.containsKey(candNode))
                for (int[] info: graph.get(candNode))
                    dist.put(info[0],
                             Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
        }

        int ans = 0;
        for (int cand: dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }
}

PQ:
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
}


Better version:
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : times){
            if (!graph.containsKey(edge[0])){
                graph.put(edge[0], new HashMap<>());
            }
            graph.get(edge[0]).put(edge[1], edge[2]);
        }
        Map<Integer, Integer> distMap = new HashMap<>();
        Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] e1, int[] e2){
                return e1[1] - e2[1];
            }
        });
        pq.offer(new int[]{K, 0});
        distMap.put(K, 0);
        while (!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            if (!graph.containsKey(node)){
                continue;
            }
            Map<Integer, Integer> map =  graph.get(node);
            for (int key : map.keySet()){
                if (!distMap.containsKey(key) || dist + map.get(key) < distMap.get(key)){
                    distMap.put(key, dist + map.get(key));
                    pq.offer(new int[]{key, dist + map.get(key)});
                }
            }
        }
        if (distMap.size() != N){
            return -1;
        }
        int max = 0;
        for (int val : distMap.values()){
            max = Math.max(max, val);
        }
        return max;
    }
}
