class UF{
        int[] parent;
        int[] size;
        int count;
        public UF(int N){
            parent = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b){
                parent[root_a] = root_b;
                size[root_b] += size[root_a];
                count--;
            }
        }
        public boolean connect(int a, int b){
            return find(a) == find(b);
        }
        public int size(){
            return count;
        }
    }
