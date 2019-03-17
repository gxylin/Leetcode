On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.

Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that is on illuminates
every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).

For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.

After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent 8-directionally
(ie., share a corner or edge with cell (x, y).)

Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].

 

Example 1:

Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
Output: [1,0]
Explanation: 
Before performing the first query we have both lamps [0,0] and [4,4] on.
The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:
1 1 1 1 1
1 1 0 0 1
1 0 1 0 1
1 0 0 1 1
1 1 1 1 1
Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks 
like this:
1 0 0 0 1
0 1 0 0 1
0 0 1 0 1
0 0 0 1 1
1 1 1 1 1
Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer 
lit.
 

Note:

1 <= N <= 10^9
0 <= lamps.length <= 20000
0 <= queries.length <= 20000
lamps[i].length == queries[i].length == 2

 
https://leetcode.com/problems/grid-illumination/discuss/243076/Java-Clean-Code-O(N)-Time-and-O(N)-Space-Beats-100
The basic idea is:

The row, column or diagonal will remain illuminated if there are >= 1 lamp
all the diagonals with 1 slope, x= y+c i.e. x-y = constant
all the diagonals with -1 slope, x= -y+c i.e x+y = constant
store the counts in separate maps
When a lamp is turned off, the count in respective row, column or diagonal decreases by 1
 
class Solution {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> mapX = new HashMap<>();
        Map<Integer, Integer> mapY = new HashMap<>();
        Map<Integer, Integer> mapSlopePos = new HashMap<>();
        Map<Integer, Integer> mapSlopeNeg = new HashMap<>();
        Set<Integer> setLamp = new HashSet<>();
        for (int[] lamp : lamps){
            int x = lamp[0];
            int y = lamp[1];
            mapX.put(x, mapX.getOrDefault(x, 0) + 1);
            mapY.put(y, mapY.getOrDefault(y, 0) + 1);
            mapSlopePos.put(x+y, mapSlopePos.getOrDefault(x+y, 0) + 1);
            mapSlopeNeg.put(x-y, mapSlopeNeg.getOrDefault(x-y, 0) + 1);
            setLamp.add(x*N+y);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++){
            int x = queries[i][0];
            int y = queries[i][1];
            if (mapX.getOrDefault(x, 0) > 0 || mapY.getOrDefault(y, 0) > 0 || mapSlopePos.getOrDefault(x+y, 0) > 0 || mapSlopeNeg.getOrDefault(x-y,0) > 0){
                res[i] = 1;
            }
            for (int r = -1; r <= 1; r++){
                for (int c = -1; c <= 1; c++){
                    int nx = x + r;
                    int ny = y + c;
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && setLamp.contains(nx*N+ny)){
                        setLamp.remove(nx*N+ny);
                        mapX.put(nx, mapX.getOrDefault(nx, 0) - 1);
                        mapY.put(ny, mapY.getOrDefault(ny, 0) - 1);
                        mapSlopePos.put(nx+ny, mapSlopePos.getOrDefault(nx+ny, 0) - 1);
                        mapSlopeNeg.put(nx-ny, mapSlopeNeg.getOrDefault(nx-ny, 0) - 1);
                    }
                }
            }
        }
        return res;
    }
}







class Solution {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int m = queries.length;
        int[] res = new int[m];
        //construct
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> lits = new HashMap<>();
        for (int[] lamp : lamps){
            int x = lamp[0];
            int y = lamp[1];
            int key = x * N + y;
            map.put(key, map.getOrDefault(key, 0) + 1);
            if (!lits.containsKey(key)){
                lits.put(key, new HashSet<>());
            }
            //lit up y-axis
            Set<Integer> set = lits.get(key);
            for (int i = 0; i < N; i++){
                if (i != x){
                    map.put(i*N+y, map.getOrDefault(i*N+y, 0) + 1);
                    set.add(i*N+y);
                }
            }
            //lit up x-axis
            for (int j = 0; j < N; j++){
                if (j != y){
                    map.put(x*N+j, map.getOrDefault(x*N+j, 0) + 1);
                    set.add(x*N+j);
                }
            }
            //lit up diagonal
            int[] dx = {1, 1, -1, -1};
            int[] dy = {1, -1, 1, -1};
            for (int i = 0; i < dx.length; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                while (nx >= 0 && nx < N && ny >= 0 && ny < N){
                    map.put(nx*N+ny, map.getOrDefault(nx*N+ny, 0) + 1);
                    nx += dx[i];
                    ny += dy[i];
                    set.add(nx*N+ny);
                }
            }
        }
        for (int k = 0; k < m; k++){
            int x = queries[k][0];
            int y = queries[k][1];
            if (map.getOrDefault(x*N+y, 0) > 0){
                res[k] = 1;
                for (int r = -1; r <= 1; r++){
                    for (int c = -1; c <= 1; c++){
                        int nx = x + r;
                        int ny = y + c;
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && lits.containsKey(nx*N+ny)){
                            Set<Integer> set = lits.get(nx*N+ny);
                            for (int on : set){
                                if (map.getOrDefault(on, 0) > 0){
                                    map.put(on, map.get(on) - 1);
                                }
                            }
                            lits.remove(nx*N+ny);
                        }
                    }
                }
            }
        }
        return res;
    }
}
