Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether
these edges make up a valid tree.

 Notice
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is 
the same as [1, 0] and thus will not appear together in edges.

这道题给了我们一个无向图，让我们来判断其是否为一棵树，我们知道如果是树的话，所有的节点必须是连接的，也就是说必须是连通图，而且不能有环，
所以我们的焦点就变成了验证是否是连通图和是否含有环。

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

Method 2: Union Find
class Solution {
    class UF{
        int[] id;
        int[] size;
        int count;
        public UF(int N){
            count = N;
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                id[i] = i;
                size[i] = 1;
            }
        }
        public int componentNum(){
            return count;
        }
        public int root(int p){
            while (p != id[p]){
                p = id[p];
            }
            return p;
        }
        public boolean find(int p , int q){
            return root(p) == root(q);
        }
        public void union(int p, int q){
            int rp = root(p);
            int rq = root(q);
            if (rp < rq){
                id[rp] = rq;
                size[rq] += size[rp];
            }else{
                id[rq] = rp;
                size[rp] += size[rq];
            }
            count--;
        }
        
    }
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges){
            if (uf.find(edge[0], edge[1])){
                return false;
            }
            uf.union(edge[0], edge[1]);
        }
        return uf.componentNum() == 1;
    }
}
https://leetcode.com/problems/graph-valid-tree/discuss/69018/AC-Java-Union-Find-solution
