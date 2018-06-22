Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++){
            graphMap.put(i, new ArrayList<Integer>());
            for (int j = 0; j < graph[i].length; j++){
                graphMap.get(i).add(graph[i][j]);
            }
        }
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(res, path, graphMap, 0,  graph.length - 1);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> path, Map<Integer, List<Integer>> graphMap, int src, int dst){
        if (src == dst){
            res.add(new ArrayList<Integer>(path));
            return;
        }
        List<Integer> list = graphMap.get(src);
        for (int node : list){
            path.add(node);
            dfs(res, path, graphMap, node, dst);
            path.remove(path.size() - 1);
        }
    }
}
