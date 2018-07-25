Given an n-ary tree, return the postorder traversal of its nodes' values.

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
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        dfs(root, res);
        return res;
    }
    private void dfs(Node root, List<Integer> res){
        if (root == null){
            return;
        }
        for (Node node : root.children){
            dfs(node, res);
        }
        res.add(root.val);
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
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            res.add(node.val);
            for (Node n : node.children){
                stack.push(n);
            }
        }
        Collections.reverse(res);
        return res;
    }
}
