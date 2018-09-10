Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:

Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 

Note:

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.


Method 1: Recursion
Time complexity: O(nlogn)
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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd){
        if (preStart > preEnd || postStart > postEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart + 1 <= preEnd){
            int deltaIndex = findIndex(post, postStart, postEnd - 1, pre[preStart+1]);
            root.left = dfs(pre, preStart + 1, preStart + 1 + deltaIndex, post, postStart, postStart + deltaIndex);
            root.right = dfs(pre,  preStart + 1 + deltaIndex + 1, preEnd, post, postStart + deltaIndex + 1, postEnd - 1);
        }
        return root;
    }
    private int findIndex(int[] post, int postStart, int postEnd, int target){
        for (int i = postStart; i <= postEnd; i++){
            if (post[i] == target){
                return i - postStart;
            }
        }
        return -1;
    }
}

Method 2: HashMap + Recursion (Better)
Time complexity: O(n + logn)
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++){
            map.put(post[i], i);
        }
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd, Map<Integer, Integer> map){
        if (preStart > preEnd || postStart > postEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart + 1 <= preEnd){
            int deltaIndex = map.get(pre[preStart+1]) - postStart;
            root.left = dfs(pre, preStart + 1, preStart + 1 + deltaIndex, post, postStart, postStart + deltaIndex, map);
            root.right = dfs(pre,  preStart + 1 + deltaIndex + 1, preEnd, post, postStart + deltaIndex + 1, postEnd - 1, map);
        }
        return root;
    }
}
