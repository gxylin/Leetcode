For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a 
function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges
(each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] 
and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are 
connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Method 1 :
Time complexity: O(n)
similar as topological sorting course
https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0 || edges == null || n != edges.length + 1){
            return result;
        }
        if (n == 1){
            result.add(0);
            return result;
        }
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        for (int i : graph.keySet()){
            if (graph.get(i).size() == 1){
                result.add(i);
            }
        }
        while (n > 2){
            n -= result.size();
            List<Integer> newResult = new ArrayList<>();
            for (int i : result){
                for (int neighbor: graph.get(i)){
                    graph.get(neighbor).remove(i);
                    if (graph.get(neighbor).size() == 1){
                        newResult.add(neighbor);
                    }
                }
            }
            result = newResult;
        }
        return result;
    }
    private Map<Integer, Set<Integer>> initializeGraph (int n, int[][] edges){
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int i = 0; i < n; i++){
            result.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            result.get(u).add(v);
            result.get(v).add(u);
        }
        return result;
    }
}

Method 2:
Time complexity: O(n^2) TLE
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0 || edges == null || n != edges.length + 1){
            return result;
        }
        Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            int mht = calculateMHT(graph, i);
            if (mht < min){
                result = new ArrayList<>();
                result.add(i);
                min = mht;
            }else if (mht == min){
                result.add(i);
            }
        }
        return result;
    }
    private Map<Integer, Set<Integer>> initializeGraph (int n, int[][] edges){
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int i = 0; i < n; i++){
            result.put(i, new HashSet<Integer>());
        }
        for (int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            result.get(u).add(v);
            result.get(v).add(u);
        }
        return result;
    }
    private int calculateMHT(Map<Integer, Set<Integer>> graph, int root){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(root);
        set.add(root);
        int height = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++){
                int curr = queue.poll();
                for (int neighbor : graph.get(curr)){
                    if (!set.contains(neighbor)){
                        queue.offer(neighbor);
                        set.add(neighbor);
                    }
                }
            }
        }
        return height;
    }
}
