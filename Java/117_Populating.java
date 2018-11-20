Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
    
    
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */


Time complexity: O(n)
Space complexity: O(1)
Level order traversal

head: the leftmost node of the current level
cur: traversal node of the current level
dummyChildHead: the dummy head of the next level
child: traversal node of the next level

public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null){
            return;
        }
        TreeLinkNode head = root;
        TreeLinkNode dummyChildHead = new TreeLinkNode(-1);
        while (head != null){
            TreeLinkNode cur = head;
            TreeLinkNode child = dummyChildHead;
            while (cur != null){
                if (cur.left != null){
                    child.next = cur.left;
                    child = child.next;
                }
                if (cur.right != null){
                    child.next = cur.right;
                    child = child.next;
                }
                cur = cur.next;
            }
            head = dummyChildHead.next;
            dummyChildHead.next = null;
        }
    }
}

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null){
            return;
        }
        TreeLinkNode head = root;
        TreeLinkNode dummyChildHead = new TreeLinkNode(-1);
        while (head != null){
            TreeLinkNode curr = head;
            TreeLinkNode child = dummyChildHead;
            while(curr != null){
                if (curr.left != null){
                    child.next = curr.left;
                    child = child.next;
                }
                if (curr.right != null){
                    child.next = curr.right;
                    child = child.next;
                }
                curr = curr.next;
            }
            head = dummyChildHead.next;
            dummyChildHead = new TreeLinkNode(-1);
        }
    }
}
