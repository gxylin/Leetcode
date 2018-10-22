(This problem is the same as Minimize Malware Spread, with the differences bolded.)

In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.

Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least one of those two nodes is infected by malware, both nodes will be infected by malware.  This spread of malware will continue until no more nodes can be infected in this manner.

Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread of malware stops.

We will remove one node from the initial list, completely removing it and any connections from this node to any other node.  Return the node that if removed, would minimize M(initial).  If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.

 

Example 1:

Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
Output: 0
Example 2:

Input: graph = [[1,1,0],[1,1,1],[0,1,1]], initial = [0,1]
Output: 1
Example 3:

Input: graph = [[1,1,0,0],[1,1,1,0],[0,1,1,1],[0,0,1,1]], initial = [0,1]
Output: 1
 

Note:

1 < graph.length = graph[0].length <= 300
0 <= graph[i][j] == graph[j][i] <= 1
graph[i][i] = 1
1 <= initial.length < graph.length
0 <= initial[i] < graph.length



This is the similar question as Leetcode 924. The code could be reused.
Please refer to https://leetcode.com/problems/minimize-malware-spread/discuss/181470/Very-Simple-Union-Find-O(N2)-no-others
for the solution of Leetcode 924

The key difference here is that the removed point will be completely isolated. So we can create union find for each case that the
initial element is removed, and calculate the total infected number in this case, then find the minimum case. If equal, find the 
smallest index.

So most of the code in Leetcode 924 could be reused here. The time complexity in this question will be O(mNN) compared to O(N*N)
in Leetcode 924.

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
        public int find(int x){
            if (parent[x] == x){
                return x;
            }
            return find(parent[x]);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int N = graph.length;
        if (initial.length == 1){
            return initial[0];
        }
        int max = N;
        int res = -1;
        for (int k = 0; k < initial.length; k++){
            UF uf = new UF(N);
            int removePt = initial[k];
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (i == removePt || j == removePt){
                        continue;
                    }
                    if (graph[i][j] == 1){
                        uf.union(i, j);
                    }
                }
            }
            int totalInfected = 0;
            Set<Integer> seen = new HashSet<>();
            for (int init : initial){
                if (init == removePt){
                    continue;
                }
                int root = uf.find(init);
                int size = uf.size[root];
                if (!seen.contains(root)){
                    seen.add(root);
                    totalInfected += size;
                }
            }
            if (max > totalInfected){
                res = removePt;
                max = totalInfected;
            }else if (totalInfected == max && removePt < res){
                res = removePt;
            }
        }
        return res;
    }
}
