The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

class Solution {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows == 1){
            return s;
        }
        
        String ans = "";
        for (int i = 0; i < numRows; i++){
            int j = i;
            String row = "";
            int step = 2 * numRows - 2;
            while (j < s.length()){
                row += s.charAt(j);
                j += step;
                int between = j - 2*i ;
                if (i != 0 && i != numRows - 1 && between < s.length()){
                    row += s.charAt(between);
                }
            }
            ans += row;
        }
        return ans;
    }
}
