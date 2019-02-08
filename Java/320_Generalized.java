Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

he idea is: for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution and carry 
on backtracking. To abbreviate it, we omit it in the current solution, but increment the count, which indicates how many
characters have we abbreviated. When we reach 
the end or need to put a character in the current solution, and count is bigger than zero, we add the number into the solution.
 
Method 1: Backtracking
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        if (word == null){
            return result;
        }
        if (word.length() == 0){
            result.add(word);
            return result;
        }
        backtrack(result, word, new StringBuilder(), 0, 0);
        return result;
    }
    private void backtrack(List<String> result, String word, StringBuilder sb, int start, int count){
        if (start == word.length()){
            int currLen = sb.length();
            if (count > 0){
                sb.append(count);
            }
            result.add(sb.toString());
            sb.setLength(currLen);
            return;
        }
        backtrack(result, word, sb, start+1, count+1); // skip current char
        //not skip
        int currLen = sb.length();
        if (count > 0){
            sb.append(count);
        }
        sb.append(word.charAt(start));
        backtrack(result, word, sb, start+1, 0);
        sb.setLength(currLen);
    }
}

Method 2: DFS Best solution
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        if (word == null){
            return result;
        }
        dfs(result, word, "", 0, 0);
        return result;
    }
    private void dfs(List<String> result, String word, String str, int start, int count){
        if (start == word.length()){
            if (count > 0){
                str += String.valueOf(count);
            }
            result.add(str);
            return;
        }
        dfs(result, word, str, start+1, count+1); // skip current char
        dfs(result, word, str + (count > 0 ? count : "") + word.charAt(start), start+1, 0);    //not skip
    }
}
