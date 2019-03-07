Given a char array representing tasks CPU need to do. It contains capital letters A to Z
where different letters represent different tasks.Tasks could be done without original order.
Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must 
be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

Method 1:
Complexity Analysis

Time complexity : O(time). Number of iterations will be equal to resultant time.

Space complexity : O(1). Constant size array map is used.

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++){
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);
        int idle = 0;
        while (map[25] > 0){
            int i = 0;
            while(i <= n){
                if (map[25] == 0){
                    break;
                }
                if (i <= 25 && map[25 - i] > 0){
                    map[25 - i]--;
                }else{
                    idle++;
                }
                i++;
            }
            Arrays.sort(map);
        }
        return tasks.length + idle;
    }
}


Method 2:
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks){
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int count = 0;
        int i = 25;
        while (i>=0 && map[i] == map[25]){
            i--;
            count++;
        }
        return Math.max(tasks.length, (map[25]-1)*(n+1) + count);
    }
}
https://leetcode.com/problems/task-scheduler/discuss/104496/concise-Java-Solution-O(N)-time-O(26)-space
Method 3: best solution
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks){
            map[c - 'A']++;
        }
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < 26; i++){
            if (map[i] > maxCount){
                maxCount = map[i];
                count = 1;
            }else if (map[i] == maxCount){
                count++;
            }
        }
        return Math.max(tasks.length, (maxCount-1)*(n+1) + count);
    }
}

better:
Corner case: when n == 1
     AAABBB 0 , n there is 0, it means same task can put together,

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int extra = 0;
        for (char key : map.keySet()){
            if (maxCount == map.get(key)){
                extra++;
            }else if (maxCount < map.get(key)){
                maxCount = map.get(key);
                extra = 1;
            }
        }
        return Math.max(tasks.length, (maxCount-1) * (n+1) + extra);
    }
}
