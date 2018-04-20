You are given an integer array nums and you have to return a new counts array. The counts array has the property
where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Method 1: Brute force O(n^2) TLE
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int count = 0;
            for (int j = i+1; j < nums.length; j++){
                if (nums[j] < nums[i]){
                    count++;
                }
            }
            result.add(count);
        }
        return result;
    }
}

Method 2: Binary tree
O(nLogn)
class Solution {
    class TreeNode {
        int val;
        int count = 1;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val){
            this.val = val;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        int end = nums.length - 1;
        TreeNode root = new TreeNode(nums[end]);
        result.add(0);
        for (int i = end - 1; i >= 0; i--){
            int count = insertNode(root, nums[i]);
            result.add(0, count);
        }
        return result;
    }
    private int insertNode(TreeNode root, int val){
        int thisCount = 0;
		while(true) {
			if(val <= root.val) {
				root.count++;
				if(root.left == null) {
					root.left = new TreeNode(val); break;
				} else {
					root = root.left;
				}
			} else {
				thisCount += root.count;
				if(root.right == null) {
					root.right = new TreeNode(val); break;
				} else {
					root = root.right;
				}
			}
		}
		return thisCount;
    }
}
