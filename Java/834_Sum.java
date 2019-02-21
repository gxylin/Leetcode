An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: 
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000

Method 1: Brute Force TLE
Time complexity: O((V+E)*V^2)
Space complexity: O(V+E)
class Solution {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (edges.length == 0){
            return new int[N];
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        //build graph
        for (int[] edge : edges){
            if (!graph.containsKey(edge[0])){
                graph.put(edge[0], new HashSet<Integer>());
            }
            Set<Integer> set0 = graph.get(edge[0]);
            set0.add(edge[1]);
            if (!graph.containsKey(edge[1])){
                graph.put(edge[1], new HashSet<Integer>());
            }
            Set<Integer> set1 = graph.get(edge[1]);
            set1.add(edge[0]);
        }
        //dfs
        int[] ans = new int[N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                ans[i] += bfs(graph, i, j);
            }
        }
        return ans;
    }
    private int bfs(Map<Integer, Set<Integer>> graph, int start, int end){
        if (start == end){
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.offer(start);
        seen.add(start);
        int dist = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int node = queue.poll();
                if (node == end){
                    return dist;
                }
                Set<Integer> set = graph.get(node);
                for (int j : set){
                    if (!seen.contains(j)){
                        queue.offer(j);
                        seen.add(j);
                    }
                }  
            }
            dist++;
        }
        return -10000;
    }
}


Method 2: Best solution
Time complexity: O(V+E)
Space complexity: O(V+E)
class Solution {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        int[] res = new int[N]; //first stores the sum distance of curr node to its subtree nodes, then stores the answer
        int[] count = new int[N]; //store the number of subtree nodes (including i) at node i
        Arrays.fill(count, 1);
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < N; i++){
            graph.put(i, new HashSet<Integer>());
        }
        for (int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        postOrder(graph, res, count, 0, new HashSet<Integer>()); //calculate the distance from 0 to all nodes
        preOrder(graph, res, count, 0, new HashSet<Integer>()); //calculate others 
        return res;
    }
  //calculate the sum of all the distance from root to nodes
    private void postOrder(Map<Integer, Set<Integer>> graph, int[] res, int[] count, int parent, Set<Integer> seen){
        seen.add(parent);
        for (int child : graph.get(parent)){
            if (!seen.contains(child)){
                seen.add(child);
                postOrder(graph, res, count, child, seen);
                count[parent] += count[child];
                res[parent] += res[child] + count[child];
            }
        }
    }
  //calculate the sum of all the distance from node to all other nodes
    private void preOrder(Map<Integer, Set<Integer>> graph, int[] res, int[] count, int parent, Set<Integer> seen){
        seen.add(parent);
        for (int child : graph.get(parent)){
            if (!seen.contains(child)){
                res[child] = res[parent] - count[child] + res.length - count[child];
                preOrder(graph, res, count, child, seen);
            }
        }
    }
}

https://leetcode.com/problems/sum-of-distances-in-tree/solution/
class Solution {
    int[] ans, count;
    List<Set<Integer>> graph;
    int N;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        graph = new ArrayList<Set<Integer>>();
        ans = new int[N];
        count = new int[N];
        Arrays.fill(count, 1);

        for (int i = 0; i < N; ++i)
            graph.add(new HashSet<Integer>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
    }

    public void dfs2(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
    }
}
