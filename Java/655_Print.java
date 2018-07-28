Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.
Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
Note: The height of binary tree is in the range of [1, 10]

Method 1: Iteration
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
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        int row = depth(root);
        int col = (int)Math.pow(2, row) - 1;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        queue.offer(root);
        index.offer(col/2);
        int len = col/2;
        for (int i = 0; i < row; i++){
            List<String> item = new ArrayList<>();
            int size = queue.size();
            int k = 0;
            for (int j = 0; j < size; j++){
                TreeNode node = queue.poll();
                int idx = index.poll();
                if (node.left != null){
                    queue.offer(node.left);
                    index.offer(idx-1-len/2);
                }
                if (node.right != null){
                    queue.offer(node.right);
                    index.offer(idx+1+len/2);
                }
                while (k < idx){
                    item.add("");
                    k++;
                }
                item.add(String.valueOf(node.val));
                k++;
            }
            while (k < col){
                item.add("");
                k++;
            }
            len /= 2;
            res.add(item);
        }
        return res;
    }
    private int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

Note 1: col = (int) Math.pow(2, row) - 1;
Note 2:  we always print a node at the center of its subtree index range. 
     root is at the center of left and right, say mid
     root.left (if not null) is at the center of left and mid - 1
     root.right (if not null) is at the center of mid + 1 and right
https://leetcode.com/problems/print-binary-tree/discuss/106269/Java-Iterative-Level-Order-Traversal-with-Queue

class Solution {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        int row = depth(root);
        int col = (int)Math.pow(2, row) - 1;
        for (int i = 0; i < row; i++){
            List<String> item = new ArrayList<>();
            for (int j = 0; j < col; j++){
                item.add("");
            }
            res.add(item);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<int[]> index = new LinkedList<>();
        queue.offer(root);
        index.offer(new int[]{0, col-1});
        int r = -1;
        for (int i = 0; i < row; i++){
            int size = queue.size();
            r++;
            for (int j = 0; j < size; j++){
                TreeNode node = queue.poll();
                int[] idx = index.poll();
                int start = idx[0];
                int end = idx[1];
                int mid = start + (end - start) / 2;
                if (node.left != null){
                    queue.offer(node.left);
                    index.offer(new int[]{start, mid-1});
                }
                if (node.right != null){
                    queue.offer(node.right);
                    index.offer(new int[]{mid+1, end});
                }
                res.get(r).set(mid, String.valueOf(node.val));
            }
        }
        return res;
    }
    private int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}

Method 2: Recursion
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
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        int row = depth(root);
        int col = (int) Math.pow(2, row) - 1;
        for (int i = 0; i < row; i++){
            List<String> item = new ArrayList<>();
            for (int j = 0; j < col; j++){
                item.add("");
            }
            res.add(item);
        }
        populate(root, res, 0, row, 0, col-1);
        return res;
    }
    private void populate(TreeNode root, List<List<String>> res, int rowStart, int rowEnd, int colStart, int colEnd){
        if (root == null || rowStart == rowEnd){
            return;
        }
        int mid = colStart + (colEnd - colStart) / 2;
        res.get(rowStart).set(mid, String.valueOf(root.val));
        populate(root.left, res, rowStart+1, rowEnd, colStart, mid - 1);
        populate(root.right, res, rowStart+1, rowEnd, mid + 1, colEnd);
    }
    private int depth(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
