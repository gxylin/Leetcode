Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these 
edges make up a valid tree.

 Notice
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is 
the same as [1, 0] and thus will not appear together in edges.

public class Solution {
    /*
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0){
            return false;
        }
        if (n != edges.length + 1){
            return false;
        }
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        set.add(0);
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (Integer neigbor : graph.get(cur)){
                if (set.contains(neigbor)){
                    continue;
                }
                set.add(neigbor);
                queue.offer(neigbor);
            }
        }
        if (set.size() == n){
            return true;
        }
        return false;
    }
    private static Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges){
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++){
            graph.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }
}
