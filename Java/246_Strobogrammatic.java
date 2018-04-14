A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.

class Solution {
    public boolean isStrobogrammatic(String num) {
        int i = 0, j = num.length()-1;
        String s = num;
        while(i<=j){
            if((s.charAt(i)=='1' && s.charAt(j)=='1')|| (s.charAt(i)=='8' && s.charAt(j)=='8')|| (s.charAt(i)=='0' && s.charAt(j)=='0')){
                i++;
                j--;
            }   
            else if((s.charAt(i)=='6' && s.charAt(j)=='9')|| (s.charAt(i)=='9' && s.charAt(j)=='6')){
                i++;
                j--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
