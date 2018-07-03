Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".


class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        Set<String> skip = new HashSet<String>(Arrays.asList(".", "..", ""));
        String[] dirs = path.split("/");
        for (String dir : dirs){
            if (dir.equals("..") && !stack.isEmpty()){
                stack.pop();
            }else if (!skip.contains(dir)){
                stack.push(dir);
            }
        }
        String res = "";
        for (String dir : stack){
            res = res + "/" + dir;
        }
        return res.length() == 0 ? "/" : res;
    }
}
