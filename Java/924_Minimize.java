In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.

Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least one of those two nodes
is infected by malware, both nodes will be infected by malware.  This spread of malware will continue until no more nodes can be
infected in this manner.

Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread of malware stops.

We will remove one node from the initial list.  Return the node that if removed, would minimize M(initial).  If multiple nodes
could be removed to minimize M(initial), return such a node with the smallest index.

Note that if a node was removed from the initial list of infected nodes, it may still be infected later as a result of the malware
spread.

 

Example 1:

Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
Output: 0
Example 2:

Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
Output: 0
Example 3:

Input: graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
Output: 1
 

Note:

1 < graph.length = graph[0].length <= 300
0 <= graph[i][j] == graph[j][i] <= 1
graph[i][i] = 1
1 <= initial.length < graph.length
0 <= initial[i] < graph.length


Union Find: the complexity of Union Find with path compression is O(1)
O(N^2)
class Solution {
    class UF {
        int[] parent;
        int[] size;
        public UF(int N){
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        private int find(int x){
            if (x == parent[x]){
                return x;
            }
            return find(parent[x]);
        }
        private void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int res = -1;
        int N = graph.length;
        UF uf = new UF(N);
        for (int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph[i].length; j++){
                if (graph[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
        int max = 0;
        for (int i : initial){
            int root = uf.find(i);
            int size = uf.size[root];
            if (max < size){
                max = size;
                res = i;
            }else if (max == size && i < res){
                res = i;
            }
        }
        return res;
    }
}
