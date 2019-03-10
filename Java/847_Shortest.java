An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple
times, and you may reuse edges.

 

Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Note:

1 <= graph.length <= 12
0 <= graph[i].length < graph.length

Method 1: BFS
https://leetcode.com/problems/shortest-path-visiting-all-nodes/discuss/152679/Short-Java-Solution-BFS-with-a-Set
https://leetcode.com/problems/shortest-path-visiting-all-nodes/solution/
https://leetcode.com/problems/shortest-path-visiting-all-nodes/discuss/135809/Fast-BFS-Solution-(46ms)-Clear-Detailed-Explanation-Included

Key points:
1. Use bit to record visited node to optimize code, otherwise it will TLE if use Set<Integer>
2. Use string to save path candidate
3. Use separate ":" to avoid repeating route since big mask alone can't do the job, it has to include vertex info

Note that to avoid repeating route: the following string format is used 
e.g. 0 -> 1 ==> set add 11:1
     1 -> 0 ==> set add 11:0
     to avoid 0 -> 1 -> 0 -> 1

class Solution {
    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        int count = 0;
        int target = 0;
        Set<String> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++){
            target |= (1 << i);
            int[] pair = new int[]{(1 << i), i};
            queue.offer(pair);
            set.add(pair[0] + ":" + pair[1]);
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] pair = queue.poll();
                if (target == pair[0]){
                    return count;
                }else{
                    int currPath = pair[0];
                    int currNode = pair[1];
                    for (int nei : graph[currNode]){
                        int newPath = (1 << nei) | currPath;
                        String key = newPath + ":" + nei;
                        if (!set.contains(key)){
                            int[] newPair = new int[]{newPath, nei};
                            queue.offer(newPair);
                            set.add(key);
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
