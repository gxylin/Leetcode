Given an n-ary tree, return the preorder traversal of its nodes' values.


Method 1: recursion
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(Node root, List<Integer> res){
        if (root == null){
            return;
        }
        res.add(root.val);
        for (Node node : root.children){
            dfs(node, res);
        }
    }
}

Method 2: Iteration
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--){
                stack.push(node.children.get(i));
            }
        }
        return res;
    }
}
