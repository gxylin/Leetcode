Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of
any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

Method 1: Serialization
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


    Time Complexity: O(N2), where N is the number of nodes in the tree. We visit each node once, but each creation of serial may take O(N)) work.

    Space Complexity: O(N2), the size of map.

serialization 1,2,#,#,3,4,#,#,5,#,#, which is a unique representation of the tree.
Perform a depth-first search, where the recursive function returns the serialization of the tree. At each node, record the result in a map, and analyze the map after to determine duplicate subtrees.

class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        dfs(root, map, res);
        return res;
    }
    private String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> res){
        if (root == null){
            return "n";
        }
        String str = root.val + "," + dfs(root.left, map, res) + "," + dfs(root.right, map, res);
        map.put(str, map.getOrDefault(str, 0) + 1);
        if (map.get(str) == 2){
            res.add(root);
        }
        return str;
    }
}

Method 2:
Time complexity: O(n)
Space complexity: O(n^2)
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        dfs(root, map, res);
        return res;
    }
    private String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> res){
        if (root == null){
            return "n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(",");
        sb.append(dfs(root.left, map, res));
        sb.append(",");
        sb.append(dfs(root.right, map, res));
        String str = sb.toString();
        map.put(str, map.getOrDefault(str, 0) + 1);
        if (map.get(str) == 2){
            res.add(root);
        }
        return str;
    }
}

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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        preOrder(res, root, map);
        return res;
    }
    private String preOrder(List<TreeNode> res, TreeNode root, Map<String, Integer> map){
        if (root == null){
            return "null,";
        }
        String str = root.val + ",";
        str += preOrder(res, root.left, map);
        str += preOrder(res, root.right, map);
        map.put(str, map.getOrDefault(str, 0) + 1);
        if (map.get(str) == 2){
            res.add(root);
        }
        return str;
    }
}

