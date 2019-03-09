A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as 
far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and 
returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.
 

Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]
 

Note:

The initial given tree is complete and contains between 1 and 1000 nodes.
CBTInserter.insert is called at most 10000 times per test case.
Every value of a given or inserted node is between 0 and 5000.


Key idea: Complete Binary Tree ==> HashMap + Label Node
class CBTInserter {
    Map<Integer, TreeNode> map;
    int count;
    TreeNode head;
    public CBTInserter(TreeNode root) {
        head = root;
        map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        count = 1;
        map.put(count++, head);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left != null){
                map.put(count++, node.left);
                queue.offer(node.left);
                if (node.right != null){
                    map.put(count++, node.right);
                    queue.offer(node.right);
                }
            }
        }
    }
    
    public int insert(int v) {
        int parentInt = count / 2;
        TreeNode parent = map.get(parentInt);
        TreeNode node = new TreeNode(v);
        if (count % 2 == 0){//insert as left node
            parent.left = node;
        }else{//insert as right node
            parent.right = node;
        }
        map.put(count++, node);
        return parent.val;
    }
    
    public TreeNode get_root() {
        return head;
    }
}
