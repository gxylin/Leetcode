Given a binary tree, we install cameras on the nodes of the tree. 

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.

 

Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations
of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.

https://leetcode.com/problems/binary-tree-cameras/discuss/211246/C++-Greedy+DFS-O(n)-Time-4ms-with-Explanation

Greedy + post order

Time complexity: O(N)
Space complexity: O(h)
class Solution {
    private int count = 0;
    private static final int NOT_MONITOR = 0;
    private static final int MONITOR_BY_OTHER = 1;
    private static final int CAMERA_HERE = 2;
    public int minCameraCover(TreeNode root) {
        if (getState(root) == NOT_MONITOR){
            count++;
        }
        return count;
    }
    private int getState(TreeNode root){
        if (root == null){
            return MONITOR_BY_OTHER;
        }
        int left = getState(root.left);
        int right = getState(root.right);
        if (left == NOT_MONITOR || right == NOT_MONITOR){
            count++;
            return CAMERA_HERE;
        }
        if (left == CAMERA_HERE || right == CAMERA_HERE){
            return MONITOR_BY_OTHER;
        }
        return NOT_MONITOR;
    }
}
