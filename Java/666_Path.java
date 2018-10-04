 If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:

    The hundreds digit represents the depth D of this node, 1 <= D <= 4.
    The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
    The units digit represents the value V of this node, 0 <= V <= 9.

Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:

Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.

Example 2:

Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.


Tree traversal
How do we solve problem like this if we were given a normal tree? Yes, traverse it, keep a root to leaf running sum. 
If we see a leaf node (node.left == null && node.right == null), we add the running sum to the final result.

Now each tree node is represented by a number. 1st digits is the level, 2nd is the position in that level 
(note that it starts from 1 instead of 0). 3rd digit is the value. We need to find a way to traverse this tree and get the sum.

The idea is, we can form a tree using a HashMap. The key is first two digits which marks the position of a node in the tree. 
The value is value of that node. Thus, we can easily find a node's left and right children using math.
Formula: For node xy? its left child is (x+1)(y*2-1)? and right child is (x+1)(y*2)?

class Solution {
    int sum = 0;
    public int pathSum(int[] nums) {
        Map<Integer, Integer> tree = new HashMap<>();
        for (int num : nums){
            int key = num / 10;
            int val = num % 10;
            tree.put(key, val);
        }
        traverse(nums[0] / 10, 0, tree);
        return sum;
    }
    private void traverse(int root, int preSum, Map<Integer, Integer> tree){
        int level = root / 10;
        int pos = root % 10;
        int left = (level + 1) * 10 + (pos * 2 - 1);
        int right = (level + 1) * 10 + pos * 2;
        int curSum = preSum + tree.get(root);
        if (!tree.containsKey(left) && !tree.containsKey(right)){
            sum += curSum;
            return;
        }
        if (tree.containsKey(left)){
            traverse(left, curSum, tree);
        }
        if (tree.containsKey(right)){
            traverse(right, curSum, tree);
        }
    }
}
