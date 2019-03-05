A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:

 

Note:

1 <= N <= 20

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
Method 1: without memo
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0){
            return res;
        }
        return getAll(N);
    }
    private List<TreeNode> getAll(int N){
        List<TreeNode> res = new ArrayList<>();
        if (N == 1){
            res.add(new TreeNode(0));
            return res;
        }
        for (int i = 1; i < N; i+= 2){
            List<TreeNode> leftList = getAll(i);
            List<TreeNode> rightList = getAll(N-1-i);
            for (TreeNode left : leftList){
                for (TreeNode right : rightList){
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}

class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N <= 0 && N % 2 == 0){
            return res;
        }
        if (N == 1){
            TreeNode root = new TreeNode(0);
            res.add(root);
            return res;
        }
        for (int i = 1; i < N; i++){
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N-1-i);
            for (TreeNode l : left){
                for (TreeNode r : right){
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
}


Method 2: add memo
Time complexity: O(2^N)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0){
            return res;
        }
        return getAll(N);
    }
    Map<Integer, List<TreeNode>> memo = new HashMap<>();
    private List<TreeNode> getAll(int N){
        List<TreeNode> res = new ArrayList<>();
        if (N == 1){
            res.add(new TreeNode(0));
            return res;
        }
        if (memo.containsKey(N)){
            return memo.get(N);
        }
        for (int i = 1; i < N; i+= 2){
            List<TreeNode> leftList = getAll(i);
            List<TreeNode> rightList = getAll(N-1-i);
            for (TreeNode left : leftList){
                for (TreeNode right : rightList){
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        memo.put(N, res);
        return res;
    }
}


class Solution {
    Map<Integer, List<TreeNode>> map = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int N) {
        if (map.containsKey(N)){
            return map.get(N);
        }
        List<TreeNode> res = new ArrayList<>();
        if (N <= 0 && N % 2 == 0){
            return res;
        }
        if (N == 1){
            TreeNode root = new TreeNode(0);
            res.add(root);
            map.put(1, res);
            return res;
        }
        for (int i = 1; i < N; i++){
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N-1-i);
            for (TreeNode l : left){
                for (TreeNode r : right){
                    TreeNode root = new TreeNode(0);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        map.put(N, res);
        return res;
    }
}
