We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string S.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
Example 2:
Input: "(00011)"
Output:  ["(0.001, 1)", "(0, 0.011)"]
Explanation: 
0.0, 00, 0001 or 00.01 are not allowed.
Example 3:
Input: "(0123)"
Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
Example 4:
Input: "(100)"
Output: [(10, 0)]
Explanation: 
1.0 is not allowed.
 

Note:

4 <= S.length <= 12.
S[0] = "(", S[S.length - 1] = ")", and the other elements in S are digits.

class Solution {
    public List<String> ambiguousCoordinates(String S) {
        List<String> res = new ArrayList<>();
        String str = S.substring(1, S.length() - 1);
        int len = str.length();
        for (int i = 1 ; i < len ; i++){
            List<String> left = getList(str.substring(0, i));
            List<String> right = getList(str.substring(i));
            for (String l : left){//if left is empty, it will not go into loop
                for (String r : right){
                    res.add("(" + l + ", " + r + ")");
                }
            }
        }
        return res;
    }
    private List<String> getList(String S){
        int len = S.length();
        List<String> res = new ArrayList<>();
        if (len == 1){//case 1: length == 1
            res.add(S);
            return res;
        }
        if (S.charAt(0) == '0' && S.charAt(len-1) == '0'){// case 2: both start and end with zero, 0012300
            return res;
        }
        if (S.charAt(0) == '0'){// case 3: start with zero 0001234
            res.add("0." + S.substring(1));
            return res;
        }
        res.add(S);
        if (S.charAt(len-1) == '0'){//case 4: end with zero, 1230
            return res;
        }
        for (int i = 1; i < len; i++){//case 5: neither start nor end with zero
            res.add(S.substring(0, i) + "." + S.substring(i));
        }
        return res;
    }
}
