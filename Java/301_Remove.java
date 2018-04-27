Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
Time complexity: O(n* 2^n)
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null){
            return result;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()){
            String str = queue.poll();
            if (isValid(str)){
                result.add(str);
                found = true;
            }
            if (found){
                continue;
            }
            for (int i = 0; i < str.length(); i++){
                char c = str.charAt(i);
                if (c != '(' && c != ')'){
                    continue;
                }
                String temp = str.substring(0, i) + str.substring(i+1);
                if (!visited.contains(temp)){
                    queue.offer(temp);
                    visited.add(temp);
                }
            }
        }
        return result;
    }
    private boolean isValid(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){
                count++;
            }else if (c == ')'){
                count--;
                if (count < 0){
                    return false;
                }
            }
        }
        return count == 0;
    }
}
