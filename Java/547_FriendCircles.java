There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. 
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we 
defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith 
and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend 
circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

Method 1: BFS -- similar to connected components
class Solution {
    public int findCircleNum(int[][] M) {
        int count = 0;
        for (int i = 0; i < M.length; i++){
            if (M[i][i] == 1){
                count++;
                bfs(i, M);
            }
        }
        return count;
    }
    private void bfs(int student, int[][] M){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(student);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int st = queue.poll();
                M[st][st] = 2; //indicate the student is visited
                for (int j = 0; j < M[0].length; j++){
                    if (M[st][j] == 1 && M[j][j] == 1){
                        queue.offer(j); 
                    }
                }
            }
        }
    }
}

Method 2: DFS
class Solution {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++){
            if (visited[i] == 0){
                count++;
                dfs(M, visited, i);
            }
        }
        return count;
    }
    private void dfs(int[][] M, int[] visited, int st){
        for (int i = 0; i < M[0].length; i++){
            if (M[st][i] == 1 && visited[i] == 0){
                visited[i] = 1;
                dfs(M, visited, i);
            }
        }
    }
}

Method 3: Union Find
class Solution {
    class UF{
        int[] id; //parent link
        int[] size; //size of component for each root id;
        int count; // number of components
        public UF(int N){
            count = N;
            id = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                id[i] = i;
                size[i] = 1;
            }
        }
        public int size(){
            return count;
        }
        public int root(int p){
            while (p != id[p]){
                p = id[p];
            }
            return p;
        }
        public boolean find(int p, int q){
            return root(p) == root(q);
        }
        public void union(int p, int q){
            int rp = root(p);
            int rq = root(q);
            if (size[rp] < size[rq]){
                id[rp] = rq;
                size[rq] += size[rp];
            }else{
                id[rq] = rp;
                size[rp] += size[rq];
            }
            count--;
        }
    }
    public int findCircleNum(int[][] M) {
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length; i++){
            for (int j = i + 1; j < M[0].length; j++){
                if (M[i][j] == 1 && !uf.find(i, j)){
                    uf.union(i, j);
                }
            }
        }
        return uf.size();
    }
}
