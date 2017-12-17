A mirror number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is mirror. The number is represented as a string.

Have you met this question in a real interview? Yes
Example
For example, the numbers "69", "88", and "818" are all mirror numbers.
Given num = "69" return true
Given num = "68" return false

public class Solution {
    /*
     * @param num: a string
     * @return: true if a number is strobogrammatic or false
     */
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0){
            return false;
        }
        for (int i = 0; i < num.length(); i++){
            if (num.charAt(i) == '2' || num.charAt(i) == '3' || num.charAt(i) == '4' || num.charAt(i) == '5' || num.charAt(i) == '7'){
                return false;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--){
            if (num.charAt(i) == '6'){
                sb.append('9');
            }else if (num.charAt(i) == '9'){
                sb.append('6');
            }else{
                sb.append(num.charAt(i));
            }
        }
        return num.equals(sb.toString());
    }
}
