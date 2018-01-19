Find distance between two nodes of BinarySearchTree
Given a list of unique integers, construct the binary tree by given order without rebalancing, 
then find out the distance between two nodes..


public class old_bstDistance {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public int bstDistance(int[] values, int node1, int node2){
        if (values == null || values.length == 0){
            return 0;
        }
        boolean node1In = false;
        boolean node2In = false;
        for (int i = 0; i < values.length; i++){
            if (values[i] == node1){
                node1In = true;
            }
            if (values[i] == node2){
                node2In = true;
            }
        }
        if (!node1In || !node2In){
            return 0;
        }
        TreeNode root = new TreeNode(values[0]);
        for (int i = 1; i < values.length; i++){
            createBST(root, values[i]);
        }
        return shortestDist(root, node1, node2);
    }

    public void createBST(TreeNode root, int value){
        if (value < root.val){
            if (root.left == null){
                root.left = new TreeNode(value);
            }else{
                createBST(root.left, value);
            }
        }else{
            if (root.right == null){
                root.right = new TreeNode(value);
            }else{
                createBST(root.right, value);
            }
        }
    }
    public TreeNode bstLCA(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q){
            return root;
        }
        if (p.val < root.val && root.val < q.val || q.val < root.val && root.val < q.val){
            return root;
        }else if (root.val > p.val && root.val > q.val){
            return bstLCA(root.left, p, q);
        }else if (root.val < p.val && root.val < q.val){
            return bstLCA(root.right, p, q);
        }
        return null;
    }
    public int shortestDist(TreeNode root, int n1, int n2){
        if (root == null){
            return 0;
        }
        if (root.val > n1 && root.val > n2){
            return shortestDist(root.left, n1, n2);
        }else if (root.val < n1 && root.val < n2){
            return shortestDist(root.right, n1, n2);
        }else{// note that root now becomes the LCA of n1 and n2
            return distFromRoot(root, n1) + distFromRoot(root, n2);
        }
    }
    public int distFromRoot(TreeNode root, int n){
        if (root == null || root.val == n){
            return 0;
        }
        if (root.val > n){
            return 1 + distFromRoot(root.left, n);
        }
        return 1 + distFromRoot(root.right, n);
    }
    public static void main(String[] args){
        int[] values = {20, 10, 5, 15, 30, 25, 55};
        old_bstDistance result = new old_bstDistance();
        int ans = result.bstDistance(values, 5, 35);
        System.out.println(ans);
    }
    /*
    We have discussed distance between two nodes in binary tree. The time complexity of this solution is O(n)

In case of BST, we can find distance faster. We start from root and for every node, we do following.

If both keys are greater than current node, we move to right child of current node.
If both keys are smaller than current node, we move to left child of current node.
If one keys is smaller and other key is greater, current node is Lowest Common Ancestor (LCA) of two nodes.
We find distances of current node from two keys and return sum of the distances.

Time complexity: O(h) where h is the height of bst
     */
}
