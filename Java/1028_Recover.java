We run a preorder depth first search on the root of a binary tree.

At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  (If the depth of a node is D, the depth of its immediate child is D+1.  The depth of the root node is 0.)

If a node has only one child, that child is guaranteed to be the left child.

Given the output S of this traversal, recover the tree and return its root.

 

Example 1:



Input: "1-2--3--4-5--6--7"
Output: [1,2,5,3,4,6,7]
Example 2:



Input: "1-2--3---4-5--6---7"
Output: [1,2,5,3,null,6,null,4,null,7]
 

Example 3:



Input: "1-401--349---90--88"
Output: [1,401,null,349,88,90]
 

Note:

The number of nodes in the original tree is between 1 and 1000. 
Each node will have a value between 1 and 10^9.

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
    public TreeNode recoverFromPreorder(String S) {
        if (S == null || S.length() == 0){
            return null;
        }
        return build(S, 1);
    }
    private TreeNode build (String S, int depth){
        if (S == null || S.length() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (depth > count){
            sb.append('-');
            count++;
        }
        String pattern = sb.toString();
        int indexRoot = findIndex(S, pattern, 1, depth);
        if (indexRoot < 0){
            return new TreeNode(Integer.parseInt(S));
        }
        TreeNode root = new TreeNode(Integer.parseInt(S.substring(0, indexRoot)));
        int indexLeft = findIndex(S, pattern, indexRoot + depth, depth);
        if (indexLeft < 0){//only has left child
            root.left = build(S.substring(indexRoot + depth), depth + 1);
            return root;
        }
        root.left = build(S.substring(indexRoot + depth, indexLeft), depth + 1);
        root.right = build(S.substring(indexLeft + depth), depth + 1);
        return root;
    }
    private int findIndex(String S, String pattern, int start, int depth){
        while (start < S.length() - depth){
            char first = S.charAt(start-1);
            char last = S.charAt(start + depth);
            String sub = S.substring(start, start + depth);
            if (Character.isDigit(first) && Character.isDigit(last) && sub.equals(pattern)){
                return start;
            }
            start++;
        }
        return -1;
    }
}
