Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently
occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space 
incurred due to recursion does not count).

Method 1:
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
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        List<Integer> list = new ArrayList<>();
        int maxFreq = 0;
        for (Integer i : map.keySet()){
            maxFreq = Math.max(maxFreq, map.get(i));
        }
        for (Integer i : map.keySet()){
            if (map.get(i) == maxFreq){
                list.add(i);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        return result;
    }
    private void helper(TreeNode root, Map<Integer, Integer> map){
        if (root == null){
            return;
        }
        helper(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        helper(root.right, map);
    }
}


Method 2: use the concept that BST in order traversal is sorted
so the question is converted to find the mode for a sorted array without using extra space
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
    int prevVal;
    int count;
    int max;
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        return result;
    }
    private void helper(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        helper(root.left, list);
        if (root.val == prevVal){
            count++;
        }else{
            prevVal = root.val;
            count = 1;
        }
        if (count > max){
            max = count;
            list.clear();
            list.add(root.val);
        }else if (count == max){
            list.add(root.val);
        }
        helper(root.right, list);
    }
}
