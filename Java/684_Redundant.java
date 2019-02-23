In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Method 1: UF
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    class UF {
        int[] parent;
        public UF (int N){
            parent = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
            }
        }
        public int find(int x){
            if (parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]); // path compression
        }
        public void union(int u, int v){
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV){
                parent[rootU] = rootV;
            }
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        UF uf = new UF(1001);
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if (uf.find(u) != uf.find(v)){
                uf.union(u, v);
            }else{
                res[0] = u;
                res[1] = v;
                return res;
            }
        }
        return res;
    }
}

Method 2: DFS
Time complexity: O(N^2)
Space complexity: O(N)
For each edge (u, v), traverse the graph with a depth-first search to see if we can connect u to v. 
If we can, then it must be the duplicate edge.
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u)){
                graph.put(u, new HashSet<>());
            }
            if (!graph.containsKey(v)){
                graph.put(v, new HashSet<>());
            }
            Set<Integer> seen = new HashSet<>();
            if (!graph.get(u).isEmpty() && !graph.get(v).isEmpty() && dfs(graph, u, v, seen)){
            //note that here we check isEmpty() to ensure it is added in the graph
            // if not yet added in the graph, no need to check dfs; if added, check if u and v are connected
                res[0] = u;
                res[1] = v;
                return res;
            }
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return res;
    }
    private boolean dfs(Map<Integer, Set<Integer>> graph, int u, int v, Set<Integer> seen){
        if (!seen.contains(u)){
            seen.add(u);
            if (u == v){
                return true;
            }
            for (int i : graph.get(u)){
                if (dfs(graph, i, v, seen)){
                    return true;
                }
            }
        }
        return false;
    }
}
