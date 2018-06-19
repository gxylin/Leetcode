There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

https://leetcode.com/problems/network-delay-time/discuss/109968/Simple-JAVA-Djikstra's-(PriorityQueue-optimized)-Solution-with-explanation
Djikstra
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
