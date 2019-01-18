Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] 
are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be 
in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].

Note: All the values of tree nodes are in the range of [-1e7, 1e7].


This solution is very simple. With every node, we associate two values/variables named inrinrinr and dcrdcrdcr, 
where incrincrincr represents the length of the longest incrementing branch below the current node including itself, 
and dcrdcrdcr represents the length of the longest decrementing branch below the current node including itself.

We make use of a recursive function longestPath(node) which returns an array of the form [inr,dcr][inr, dcr][inr,dcr] 
for the calling node. We start off by assigning both inrinrinr and dcrdcrdcr as 1 for the current node. This is 
because the node itself always forms a consecutive increasing as well as decreasing path of length 1.

Then, we obtain the length of the longest path for the left child of the current node using longestPath[root.left]. 
Now, if the left child is just smaller than the current node, it forms a decreasing sequence with the current node. 
Thus, the dcrdcrdcr value for the current node is stored as the left child's dcrdcrdcr value + 1. But, if the left 
child is just larger than the current node, it forms an incrementing sequence with the current node. Thus, we update
the current node's inrinrinr value as left_child(inr)+1left\_child(inr) + 1left_child(inr)+1.

Then, we do the same process with the right child as well. But, for obtaining the inrinrinr and dcrdcrdcr value for 
the current node, we need to consider the maximum value out of the two values obtained from the left and the right 
child for both inrinrinr and dcrdcrdcr, since we need to consider the longest sequence possible.

Further, after we've obtained the final updated values of inrinrinr and dcrdcrdcr for a node, we update the 
length of the longest consecutive path found so far as maxval=max(inr+dcr−1)maxval = \text{max}(inr + dcr - 1)
maxval=max(inr+dcr−1).

Complexity Analysis

    Time complexity : O(n). The whole tree is traversed only once.
    Space complexity : O(n). The recursion goes upto a depth of nnn in the worst case.

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
    private int max = 0;
    public int longestConsecutive(TreeNode root) {
        traverse(root);
        return max;
    }
    private int[] traverse(TreeNode root){
        if (root == null){
            return new int[]{0, 0};
        }
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int decr = 1;
        int incr = 1;
        if (root.left != null){
            if (root.val == root.left.val + 1){
                decr = left[0] + 1;
            }else if (root.val == root.left.val - 1){
                incr = left[1] + 1;
            }
        }
        if (root.right != null){
            if (root.val == root.right.val + 1){
                decr = Math.max(decr, right[0] + 1);
            }else if (root.val == root.right.val - 1){
                incr = Math.max(incr, right[1] + 1);
            }
        }
        max = Math.max(max, decr + incr - 1);
        return new int[]{decr, incr};
    }
}

Best solution
https://github.com/optimisea/Leetcode/blob/master/Java/687_Longest.java

class Solution {
    public int longestConsecutive(TreeNode root) {
        int[] max = new int[1];
        longestIncludeRoot(root, max);
        return max[0];
    }
    private int[] longestIncludeRoot(TreeNode root){
         if (root == null){
            return new int[]{0, 0};//{incr, decr}
         }
         int[] left = longestIncludeRoot(root.left);
         int[] right = longestIncludeRoot(root.right);
         int[] incr = new int[]{1, 1};//incr[0]: left longest; incr[1]: right longest
         int[] decr = new int[]{1, 1};//decr[0]: left longest: decr[1]: right longest
         if (root.left != null){
            if (root.val + 1 == root.left.val){
               incr[0] = left[0] + 1;
            }else if (root.val - 1 == root.left.val){
               decr[0] = left[1] + 1;
            }
         }
         if (root.right != null){
            if (root.val + 1 == root.right.val){
               incr[1] = right[0] + 1;
            }else if (root.val - 1 == root.right.val){
               decr[1] = right[1] + 1;
            }
         }
         max[0] = Math.max(max[0], Math.max(incr[0] + decr[1] - 1, incr[1] + decr[0] - 1));
         return new int[]{Math.max(incr[0], incr[1]), Math.max(decr[0], decr[1]};
    }
}
