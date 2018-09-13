We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6
 

Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.

Method: BFS
Similar as 847. Shortest Path Visiting All Nodes
Use bit to represent the key
Use key + ":" + x + ":" + y to represent the visited point to avoid repeating route

class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int lowcaseNum = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                char c = grid[i].charAt(j);
                if (c == '@'){
                    int[] state = new int[]{0, i, j};
                    queue.offer(state);
                    set.add(state[0] + ":" + state[1] + ":" + state[2]);
                }else if (Character.isLowerCase(c)){
                    lowcaseNum++;
                }
            }
        }
        int target = 0;
        for (int i = 0; i < lowcaseNum; i++){
            target |= (1 << i);
        }
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] state = queue.poll();
                if (state[0] == target){
                    return count;
                }
                int key = state[0];
                int cx = state[1];
                int cy = state[2];
                for (int[] dir : dirs){
                    int nx = cx + dir[0];
                    int ny = cy + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n){
                        char c = grid[nx].charAt(ny);
                        if (Character.isLowerCase(c)){//get the key
                            int newKey = key  | (1 << ((int)(c -'a')));
                            String newStr = newKey + ":" + nx + ":" + ny;
                            if (!set.contains(newStr)){
                                queue.offer(new int[]{newKey, nx, ny});
                                set.add(newStr);
                            }
                        }else if (Character.isUpperCase(c)){
                            if ((key & (1 << (int)(c - 'A'))) != 0){//already has the key for this lock
                                String newStr = key + ":" + nx + ":" + ny;
                                if (!set.contains(newStr)){
                                    queue.offer(new int[]{key, nx, ny});
                                    set.add(newStr);
                                }
                            }
                        }else if (c == '.' || c == '@'){
                            String newStr = key + ":" + nx + ":" + ny;
                            if (!set.contains(newStr)){
                                queue.offer(new int[]{key, nx, ny});
                                set.add(newStr);
                            }
                        }
                    }
                }
                
            }
            count++;
        }
        return -1;
    }
}
