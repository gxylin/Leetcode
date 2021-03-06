Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)

 
 
Method 1: Top Down
class Solution {
    String res = "";
    public String smallestFromLeaf(TreeNode root) {
        if (root == null){
            return "";
        }
        dfs(root, "");
        return res; 
    }
    private void dfs(TreeNode root, String str){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            str = (char)(root.val + 'a') + str;
            if (res.equals("") || str.compareTo(res) < 0){
                res = str;// or res = new String(str);
            }
            return;
        }
        str = (char)(root.val + 'a') + str;
        dfs(root.left, str);
        dfs(root.right, str);
    }
}

best solution
class Solution {
    String res = "";
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, "");
        return res;
    }
    private void dfs(TreeNode root, String prev){
        if (root == null){
            return;
        }
        String curr = (char)('a' + root.val) + prev;
        if (root.left == null && root.right == null){//check if it is leaf
            if (res.equals("") || curr.compareTo(res) < 0){
                res = curr;
            }
        }
        dfs(root.left, curr);
        dfs(root.right, curr);
    }
}

Method 2: without global
Top Down
class Solution {
    public String smallestFromLeaf(TreeNode root) {
        if (root == null){
            return "";
        }
        String[] res = new String[1];
        dfs(root, "", res);
        return res[0]; 
    }
    private void dfs(TreeNode root, String str, String[] res){
        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            str = (char)(root.val + 'a') + str;
            if (res[0] == null || str.compareTo(res[0]) < 0){
                res[0] = str;
            }
            return;
        }
        str = (char)(root.val + 'a') + str;
        dfs(root.left, str, res);
        dfs(root.right, str, res);
    }
}


class Solution {
    public String smallestFromLeaf(TreeNode root) {
        String[] res = new String[1];
        res[0] = "";
        dfs(root, "", res);
        return res[0];
    }
    private void dfs(TreeNode root, String prev, String[] res){
        if (root == null){
            return;
        }
        String curr = (char)('a' + root.val) + prev;
        if (root.left == null && root.right == null){//check if it is leaf
            if (res[0].equals("") || curr.compareTo(res[0]) < 0){
                res[0] = curr;
            }
        }
        dfs(root.left, curr, res);
        dfs(root.right, curr, res);
    }
}

Methdo 3: Bottom up

class Solution {
    public String smallestFromLeaf(TreeNode root) {
        return dfs(root);
    }
    private String dfs(TreeNode root){
        if (root == null){
            return null;
        }
        String left = dfs(root.left);
        String right =dfs(root.right);
        char c = (char)(root.val + 'a');
        if (left == null && right == null){
            return "" + c;
        }else if (left != null && right == null){
            return left + c;
        }else if (left == null && right != null){
            return right + c;
        }
        if (left.compareTo(right) < 0){
            return left + c;
        }
        return right + c;
    }
}
