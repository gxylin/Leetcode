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
        List<String> res = new ArrayList<>();
        if (s == null){
            return res;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;
        while(!queue.isEmpty() && !found){
            int size = queue.size();
            for (int j = 0; j < size; j++){
                String curr = queue.poll();
                if (isValid(curr)){
                    res.add(curr);
                    found = true;
                }
                if (!found){
                    for (int i = 0; i < curr.length(); i++){
                        char c = curr.charAt(i);
                        if (Character.isLetter(c)){
                            continue;
                        }
                        String str = curr.substring(0, i) + curr.substring(i+1);
                        if (!visited.contains(str)){
                            queue.offer(str);
                            visited.add(str);
                        }
                    }
                }
            }            
        }
        return res;
    }
    private boolean isValid(String str){
        int count = 0;
        for (char c : str.toCharArray()){
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

Another way to check is valid parenthesis without stack
private boolean isValid(String str){
        int count = 0;
        for (char c : str.toCharArray()){
            if (c == '('){
                count++;
            }else if (c == ')'){
                count--;
                if (count < 0){
                    return false;
                }
            }
        }
        count = 0;
        for (int i = str.length() - 1; i >= 0; i--){
            char c = str.charAt(i);
            if (c == ')'){
                count++;
            }else if (c == '('){
                count--;
                if (count <0){
                    return false;
                }
            }
        }
        return true;
    }

Check Leetcode 921: minimum add to make parenthesis valid
https://github.com/optimisea/Leetcode/blob/master/Java/921_Minimum.java
