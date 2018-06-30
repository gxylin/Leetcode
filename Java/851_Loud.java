In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.

For convenience, we'll call the person with label x, simply "person x".

We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.

Also, we'll say quiet[x] = q if person x has quietness q.

Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.

 

Example 1:

Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
Output: [5,5,2,5,4,5,6,7]
Explanation: 
answer[0] = 5.
Person 5 has more money than 3, which has more money than 1, which has more money than 0.
The only person who is quieter (has lower quiet[x]) is person 7, but
it isn't clear if they have more money than person 0.

answer[7] = 7.
Among all people that definitely have equal to or more money than person 7
(which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
is person 7.

The other answers can be filled out with similar reasoning.
Note:

1 <= quiet.length = N <= 500
0 <= quiet[i] < N, all quiet[i] are different.
0 <= richer.length <= N * (N-1) / 2
0 <= richer[i][j] < N
richer[i][0] != richer[i][1]
richer[i]'s are all different.
The observations in richer are all logically consistent.

Method 1: DFS + memo
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] res = new int[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] row : richer){
            if (!map.containsKey(row[1])){
                Set<Integer> set = new HashSet<Integer>();
                map.put(row[1], set);
            }
            map.get(row[1]).add(row[0]);
        }
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < n; i++){
            res[i] = i;
            if (map.containsKey(i)){
                res[i] = dfs(map, quiet, i, memo);
            }
        }
        return res;
    }
    private int dfs(Map<Integer, Set<Integer>> map, int[] quiet, int i, Map<Integer, Integer> memo){
        if (memo.containsKey(i)){
            return memo.get(i);
        }
        if (!map.containsKey(i)){
            return i;
        }
        Set<Integer> set = map.get(i);
        int minIndex = i;
        for (int key : set){
            int index = dfs(map, quiet, key, memo);
            if (quiet[index] < quiet[minIndex]){
                minIndex = index;
            }
        }
        memo.put(i, minIndex);
        return minIndex;
    }
}

Method 2: use res as memo + DFS
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] res = new int[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] row : richer){
            if (!map.containsKey(row[1])){
                Set<Integer> set = new HashSet<Integer>();
                map.put(row[1], set);
            }
            map.get(row[1]).add(row[0]);
        }
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++){
            if (map.containsKey(i)){
                res[i] = dfs(map, quiet, i, res);
            }else{
                res[i] = i;
            }
        }
        return res;
    }
    private int dfs(Map<Integer, Set<Integer>> map, int[] quiet, int i, int[] res){
        if (res[i] != -1){
            return res[i];
        }
        if (!map.containsKey(i)){
            return i;
        }
        Set<Integer> set = map.get(i);
        res[i] = i;
        for (int key : set){
            int index = dfs(map, quiet, key, res);
            if (quiet[index] < quiet[res[i]]){
                res[i] = index;
            }
        }
        return res[i];
    }
}

Intuition

Consider the directed graph with edge x -> y if y is richer than x.

For each person x, we want the quietest person in the subtree at x.

Algorithm

Construct the graph described above, and say dfs(person) is the quietest person in the subtree at person. Notice because the statements are logically consistent, the graph must be a DAG - a directed graph with no cycles.

Now dfs(person) is either person, or min(dfs(child) for child in person). That is to say, the quietest person in the subtree is either the person itself, or the quietest person in some subtree of a child of person.

We can cache values of dfs(person) as answer[person], when performing our post-order traversal of the graph. That way, we don't repeat work. This technique reduces a quadratic time algorithm down to linear time.


class Solution {
    ArrayList<Integer>[] graph;
    int[] answer;
    int[] quiet;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;
        graph = new ArrayList[N];
        answer = new int[N];
        this.quiet = quiet;

        for (int node = 0; node < N; ++node)
            graph[node] = new ArrayList<Integer>();

        for (int[] edge: richer)
            graph[edge[1]].add(edge[0]);

        Arrays.fill(answer, -1);

        for (int node = 0; node < N; ++node)
            dfs(node);
        return answer;
    }

    public int dfs(int node) {
        if (answer[node] == -1) {
            answer[node] = node;
            for (int child: graph[node]) {
                int cand = dfs(child);
                if (quiet[cand] < quiet[answer[node]])
                    answer[node] = cand;
            }
        }
        return answer[node];
    }
}
