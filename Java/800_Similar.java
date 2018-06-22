In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"

Example 1:
Input: color = "#09f166"
Output: "#11ee66"
Explanation:  
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.

Note:

    color is a string of length 7.
    color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
    Any answer which has the same (highest) similarity as the best answer will be accepted.
    All inputs and outputs should use lowercase letters, and the output is 7 characters.



class Solution {
    public String similarRGB(String color) {
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 1; i < color.length(); i += 2){
            String sub = color.substring(i, i+2);
            String min = getSimilar(sub);
            sb.append(min);
        }
        return sb.toString();
    }
    private String getSimilar(String s){
        if (s.charAt(0) == s.charAt(1)){
            return s;
        }
        String ans = "";
        int num = Integer.parseInt(s, 16);
        char[] chars = {'f','0', '1', '2', '3', '4', '5','6','7','8','9','a','b','c','d','e','f', '0'};
        for (int i = 1; i < chars.length-1; i++){
            char c = s.charAt(0);
            int min = Integer.MAX_VALUE;
            if (c == chars[i]){
                if (Math.abs(num - Integer.parseInt(chars[i+1] + "" + chars[i+1], 16)) < min){
                    ans = chars[i+1] + "" + chars[i+1];
                    min = Math.abs(num - Integer.parseInt(ans, 16));
                }
                if (Math.abs(num - Integer.parseInt(chars[i] + "" + chars[i], 16)) < min){
                    ans = chars[i] + "" + chars[i];
                    min = Math.abs(num - Integer.parseInt(ans, 16));
                }
                if (Math.abs(num - Integer.parseInt(chars[i-1] + "" + chars[i-1], 16)) < min){
                    ans = chars[i-1] + "" + chars[i-1];
                    min = Math.abs(num - Integer.parseInt(ans, 16));
                }
                break;
            }
        }
        return ans;
    }
}
