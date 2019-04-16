There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you 
should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish 
all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct 
course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about 
how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0){
            return new int[0];
        }
        int[] result = new int[numCourses];
        int index = 0;
        int[] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < prerequisites.length; i++){
            inDegree[prerequisites[i][0]]++;
        }
        for (int i = 0 ; i < inDegree.length; i++){
            if (inDegree[i] == 0){
               queue.offer(i);
               result[index] = i;
               index++;
            }
        }
        while (!queue.isEmpty()){
            int cur = queue.poll();
            for (int i = 0; i < prerequisites.length; i++){
                if (prerequisites[i][1] == cur){
                    inDegree[prerequisites[i][0]]--;
                    if (inDegree[prerequisites[i][0]] == 0){
                        queue.offer(prerequisites[i][0]);
                        result[index] = prerequisites[i][0];
                        index++;
                    }
                }
            }
        }
        if (index == numCourses){
            return result;
        }
        return new int[0];
    }
}


Better version: the same as course schedule I
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int index = 0;
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites){
            indegree[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int curr = queue.poll();
            res[index++] = curr;
            for (int[] p : prerequisites){
                if (p[1] == curr){
                    indegree[p[0]]--;
                    if (indegree[p[0]] == 0){
                        queue.offer(p[0]);
                    }
                }
            }
        }
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] != 0){
                return new int[0];
            }
        }
        return res;
    }
}


Best solution:
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pre : prerequisites){
            indegree[pre[0]]++;
            if (!map.containsKey(pre[1])){
                map.put(pre[1], new HashSet<>());
            }
            map.get(pre[1]).add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()){
            int curr = queue.poll();
            list.add(curr);
            if (map.containsKey(curr)){
                Set<Integer> set = map.get(curr);
                for (int next : set){
                    indegree[next]--;
                    if (indegree[next] == 0){
                        queue.offer(next);
                    }
                }
            }
        }
        if (list.size() < numCourses){
            return new int[0];
        }
        int index = 0;
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++){
            res[index++] = list.get(i);
        }
        return res;
    }
}
