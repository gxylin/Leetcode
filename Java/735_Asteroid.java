We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.
Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each other.
Note:

The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000].

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i : asteroids){
            if (stack.isEmpty() || i * stack.peek() > 0 || stack.peek() < 0 && i > 0){
                stack.push(i);
            }else if (stack.peek() > 0 && i < 0){
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < i * (-1)){
                    stack.pop();
                }
                if (stack.isEmpty()){
                    stack.push(i);
                }else if (stack.peek() < 0){
                    stack.push(i);
                }else if (stack.peek() == i * (-1)){
                    stack.pop();
                }
            }
        }
        int n = stack.size();
        if (n == 0){
            return new int[0];
        }
        int[] res = new int[n];
        // int index = 0; //this code also works but in reverse order
        // for (int i : stack){
        //     res[index++] = i;
        // }
        int index = n - 1;
        while (!stack.isEmpty()){
            res[index--] = stack.pop();
        }
        return res;
    }
}
