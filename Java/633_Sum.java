Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

Method 1: Two points (Best solution)
Time compleixty: O(sqrt(n))
Space compleixty: O(1)
class Solution {
    public boolean judgeSquareSum(int c) {
        int left = 0; 
        int right = (int)Math.sqrt(c);
        while (left <= right){
            int val = left * left + right * right;
            if (val == c){
                return true;
            }else if (val < c){
                left++;
            }else{
                right--;
            }
        }
        return false;
    }
}


Method 2: HashSet
Time complexity: O(sqrt(n))
Space complexity: O(sqrt(n))

class Solution {
    public boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= Math.sqrt(c); i++){
            set.add(i * i);
            if (set.contains(c - i * i)){
                return true;
            }
        }
        return false;
    }
}

