A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:

sx, sy, tx, ty will all be integers in the range [1, 10^9].

https://leetcode.com/problems/reaching-points/discuss/114856/Easy-and-Concise-2-line-SolutionPythonC++Java

Search from target to source instead of source to target because it's always easy to get the root 
    because you have exact one way to get the parent node, think as tree
    
 using ty%=tx instead of while (tx > ty) { tx -= ty }


class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy){
            if (tx > ty){
                tx %= ty;
            }else{
                ty %= tx;
            }
        }
        if (tx == sx && sy <= ty && (ty - sy) % sx == 0){
            return true;
        }
        if (ty == sy && sx <= tx && (tx - sx) % sy == 0){
            return true;
        }
        return false;
    }
}
