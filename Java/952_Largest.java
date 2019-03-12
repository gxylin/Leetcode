Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.


    
Best Solution:

Time complexity: O(N*sqrt(Max val of A[i]))
Union Find template. The only additional stuff is one hashmap which is used to convert factor to the node index 
in A for union.
HashMap: key is the factor, val is the index in A
    
class Solution {
    class UF {
        int[] parent;
        int[] size;
        int max;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            max = 1;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                max = Math.max(max, size[rootY]);
            }
        }
    }
    public int largestComponentSize(int[] A) {
        int N = A.length;
        Map<Integer, Integer> map = new HashMap<>();// index is the factor, val is the node index
        UF uf = new UF(N);
        for (int i = 0; i < N; i++){
            int a = A[i];
            for (int j = 2; j * j <= a; j++){
                if (a % j == 0){
                    if (!map.containsKey(j)){
                        map.put(j, i);
                    }else{
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(a/j)){
                        map.put(a/j, i);
                    }else{
                        uf.union(i, map.get(a/j));
                    }
                }
            }
            if (!map.containsKey(a)){//a could be factor too. Don't miss this
                map.put(a, i);
            }else{
                uf.union(i, map.get(a));
            }
        }
        return uf.max;
    }
}
    
Method 1: 
Time complexity: O(N*sqrt(Max val of A[i]))
Union Find template. The only additional stuff is one hashmap factorToNode which is used to convert factor to the node index 
in A for union.

class Solution {
    class UF {
        int[] parent;
        int[] size;
        int max;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            max = 1;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
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
                max = Math.max(max, size[rootY]);
            }
        }
    }
    public int largestComponentSize(int[] A) {
        int N = A.length;
        int maxNum = 0;
        for (int a : A){
            maxNum = Math.max(maxNum, a);
        }
        int[] factorToNode = new int[maxNum+1];// index is the factor, val is the node index
        Arrays.fill(factorToNode, -1);
        UF uf = new UF(N);
        for (int i = 0; i < N; i++){
            int a = A[i];
            for (int j = 2; j * j <= a; j++){
                if (a % j == 0){
                    if (factorToNode[j] == -1){
                        factorToNode[j] = i;
                    }else{
                        uf.union(i, factorToNode[j]);
                    }
                    if (factorToNode[a/j] == -1){
                        factorToNode[a/j] = i;
                    }else{
                        uf.union(i, factorToNode[a/j]);
                    }
                }
            }
            if (factorToNode[a] == -1){ // the number itself could be one factor too. Don't miss this.
                factorToNode[a] = i;
            }else{
                uf.union(i, factorToNode[a]);
            }
        }
        return uf.max;
    }
}


Method 2: calculate gcd or brute force and Union Find, TLE
class Solution {
    class UF {
        int[] parent;
        int[] size;
        int count;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
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
                count--;
            }
        }
    }
    public int largestComponentSize(int[] A) {
        int N = A.length;
        UF uf = new UF(N);
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (hasCommon(A[i], A[j])){
                    uf.union(i, j);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++){
            int root = uf.find(i);
            int size = uf.size[root];
            max = Math.max(max, size);
        }
        return max;
    }
    private boolean hasCommon(int a, int b){
        if (a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        int gcd = 0;
        while (b != 0){
            gcd = b;
            b = a % b;
            a = gcd;
        }
        return gcd > 1;
    }
}

