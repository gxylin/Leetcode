Given a binary tree where every node has a unique value, and a target key k, find the value of the 
nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any 
leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual 
root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

Convert to undirected graph using dfs and use bfs to find the minimal path
          
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
    public int findClosestLeaf(TreeNode root, int k) {
        Map<Integer, List<TreeNode>> graph = new HashMap<>();
        buildGraph(root, null, graph);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(k);
        set.add(k);
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            for (TreeNode node : graph.get(curr)){
                if (node.left == null && node.right == null){
                    return node.val;
                }
                if (!set.contains(node.val)){
                    queue.offer(node.val);
                    set.add(node.val);
                }
            }
        }
        return 0;
    }
    private void buildGraph(TreeNode root, TreeNode parent,  Map<Integer, List<TreeNode>> graph){
        if (root == null){
            return;
        }
        graph.put(root.val, new ArrayList<TreeNode>());
        graph.get(root.val).add(root);
        if (root.left != null){
            graph.get(root.val).add(root.left);
        }
        if (root.right != null){
            graph.get(root.val).add(root.right);
        }
        if (parent != null){
            graph.get(root.val).add(parent);
        }
        buildGraph(root.left, root, graph);
        buildGraph(root.right, root, graph);
    }
}

Method 2:
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap();
        dfs(graph, root, null);

        Queue<TreeNode> queue = new LinkedList();
        Set<TreeNode> seen = new HashSet();

        for (TreeNode node: graph.keySet()) {
            if (node != null && node.val == k) {
                queue.add(node);
                seen.add(node);
            }
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (graph.get(node).size() <= 1)
                    return node.val;
                for (TreeNode nei: graph.get(node)) {
                    if (!seen.contains(nei)) {
                        seen.add(nei);
                        queue.add(nei);
                    }
                }
            }
        }
        throw null;
    }

    public void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node != null) {
            if (!graph.containsKey(node)) graph.put(node, new LinkedList<TreeNode>());
            if (!graph.containsKey(parent)) graph.put(parent, new LinkedList<TreeNode>());
            graph.get(node).add(parent);
            graph.get(parent).add(node);
            dfs(graph, node.left, node);
            dfs(graph, node.right, node);
        }
    }
}
