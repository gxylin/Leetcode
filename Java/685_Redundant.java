In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

class Solution {
    class UF {
        int[] parent;
        public UF (int N){
            parent = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
            }
        }
        public int find (int x){
            if (parent[x] == x){
                return x;
            }
            return find(parent[x]);
        }
        public void union (int u, int v){//set the root of u to be the root of v
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV){
                parent[rootV] = rootU;
            }
        }
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] cand1 = {-1, -1};
        int[] cand2 = {-1, -1};
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges){
            if (!map.containsKey(edge[1])){
                map.put(edge[1], edge[0]);
            }else{
                cand1[0] = map.get(edge[1]);
                cand1[1] = edge[1];
                cand2[0] = edge[0];
                cand2[1] = edge[1];
            }
        }
        //union find to check which cand is the answer. 
        //There are 3 cases
        if (cand1[0] == -1 && cand1[1] == -1){//Case 1: no node has two parents, but the graph has a circle
            return findCircleEdge(edges, new int[]{0, 0});
        }
        int[] res = findCircleEdge(edges, cand2);//return the answer that occurs last
        if (res[0] == 0 && res[1] == 0){//Case 2: remove cand2, tree is valid, so cand2 is the answer
            return cand2;
        }
        return cand1;//Case 3: cand1 is the answer
    }
    private int[] findCircleEdge(int[][] edges, int[] edgeToRemove){
        int[] res = new int[2];
        UF uf = new UF(1001);
        for (int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            if (u == edgeToRemove[0] && v == edgeToRemove[1]){
                continue;
            }
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
