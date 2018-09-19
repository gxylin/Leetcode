Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Note:

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].

DFS
Time complexity: O(V + E) 
class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        //corner case
        if (dislikes == null || dislikes.length == 0){
            return true;
        }
        //build graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] d : dislikes){
            if (!graph.containsKey(d[0])){
                graph.put(d[0], new HashSet<>());
            }
            graph.get(d[0]).add(d[1]);
            if (!graph.containsKey(d[1])){
                graph.put(d[1], new HashSet<>());
            }
            graph.get(d[1]).add(d[0]); 
        }
        //now it will be the same as Leetcode #785
        int[] color = new int[N+1];
        for (int i = 1; i <= N; i++){
            if (!graph.containsKey(i)){
                continue;
            }
            if (color[i] == 0){
                color[i] = 1;
            }
            if (isConflict(graph, color, i)){
                return false;
            }
        }
        return true;
    }
    private boolean isConflict(Map<Integer, Set<Integer>> graph, int[] color, int start){
        for (int nei : graph.get(start)){
            if (color[nei] == 0){
                color[nei] = -color[start];
                if (isConflict(graph, color, nei)){
                    return true;
                }
            }else{
                if (color[nei] == color[start]){
                    return true;
                }
            }
        }
        return false;
    }
}
