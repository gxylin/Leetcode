You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.
Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.
Note:
target will be a non-zero integer in the range [-10^9, 10^9].

Method 1: BFS TLE
class Solution {
    public int reachNumber(int target) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                int curr = queue.poll();
                int left = curr - count;
                int right = curr + count;
                if (left == target || right == target){
                    return count;
                }
                queue.offer(left);
                queue.offer(right);
            }
        }
        return -1;
    }
}

Method 2: Math
https://leetcode.com/problems/reach-a-number/discuss/112968/Short-JAVA-Solution-with-Explanation
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0;
        int sum = 0;
        while (target > sum){
            step++;
            sum += step;
        }
        while ((target - sum) % 2 != 0){
            step++;
            sum += step;
        }
        return step;
    }
}



class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0;
        while (target > 0){
            step++;
            target -= step;
        }
        return target % 2 == 0 ? step : step + 1 + step % 2;
    }
}
