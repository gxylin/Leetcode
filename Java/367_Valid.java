Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

class Solution {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;
        int target = num;
        while (start <= end){
            long mid = start + (end - start) / 2;
            if (mid * mid == target){
                return true;
            }else if (mid * mid < target){
                start = (int) mid+1;
            }else{
                end = (int) mid-1;
            }
        }
        
        return false;
    }
}
 note is that we have to use long for mid to avoid mid*mid
 from overflow. Also, you can use long type for low and high to avoid type casting for mid from long to int.
