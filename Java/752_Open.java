 You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.

Example 4:

Input: deadends = ["0000"], target = "8888"
Output: -1

Note:

    The length of deadends will be in the range [1, 500].
    target will not be in the list deadends.
    Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'
    
 Method : BFS
 Best solution
 class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        for (String s : deadends){
            set.add(s);
        }
        if (set.contains("0000")){
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int k = 0; k < size; k++){
                String curr = queue.poll();
                if (target.equals(curr)){
                    return level;
                }
                for (int i = 0; i < 4; i++){
                    for (int j = -1; j <= 1; j+= 2){
                        int d = ((curr.charAt(i) - '0') + j + 10) % 10;
                        String s = curr.substring(0, i) + d + curr.substring(i+1);
                        if (!visited.contains(s) && !set.contains(s)){
                            visited.add(s);
                            queue.offer(s);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
}

 class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet();
        for (String d: deadends) dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }
}

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        for (String s : deadends){
            set.add(s);
        }
        if (set.contains("0000")){
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer("0000");
        visited.add("0000");
        int[] d = {-1000, -100, -10, -1, 1000, 100, 10, 1};
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int k = 0; k < size; k++){
                String curr = queue.poll();
                if (target.equals(curr)){
                    return level;
                }
                for (int i = 0; i < d.length; i++){
                    int currNum = Integer.parseInt(curr);
                    int newNum = currNum;
                    if (i < 4 && curr.charAt(i) == '0'){
                        newNum += -9 * d[i];
                    }else if (i >= 4 && curr.charAt(i-4) == '9'){
                        newNum += - 9 * d[i];
                    }else{
                        newNum += d[i];
                    }
                    String str = String.format("%04d", newNum);

                    if (!visited.contains(str) && !set.contains(str)){
                        queue.offer(str);
                        visited.add(str);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}

  for(int i = 0; i < 4; i ++) {
     char c = sb.charAt(i);
     String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
     String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
     if(!visited.contains(s1) && !deads.contains(s1)) {
         q.offer(s1);
         visited.add(s1);
     }
     if(!visited.contains(s2) && !deads.contains(s2)) {
         q.offer(s2);
         visited.add(s2);
      }
}
 
