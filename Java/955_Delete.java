We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is
["bef","vyz"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order
(A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

Return the minimum possible value of D.length.

 

Example 1:

Input: ["ca","bb","ac"]
Output: 1
Explanation: 
After deleting the first column, A = ["a", "b", "c"].
Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
Example 2:

Input: ["xc","yb","za"]
Output: 0
Explanation: 
A is already in lexicographic order, so we don't need to delete anything.
Note that the rows of A are not necessarily in lexicographic order:
ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
Example 3:

Input: ["zyx","wvu","tsr"]
Output: 3
Explanation: 
We have to delete every column.
 

Note:

1 <= A.length <= 100
1 <= A[i].length <= 100


All Greedy Methods
https://leetcode.com/problems/delete-columns-to-make-sorted-ii/solution/

Method 1: 
Instead of thinking about column deletions, let's think about which columns we will keep in the final answer.
Start with no columns kept. For each column, if we could keep it and have a valid answer, keep it - otherwise delete it because
the requirement for next column will be weaker if we keep previous column as we only need to sort it only if previous columns share 
the same letter.

Time complexity: O(mn^2)
Space complexity: O(mn)
class Solution {
    public int minDeletionSize(String[] A) {
        int m = A.length;
        int n = A[0].length();
        int res = 0;
        String[] prev = new String[m];
        for (int j = 0; j < n; j++){
            String[] curr = Arrays.copyOf(prev, m);
            for (int i = 0; i < m; i++){
                curr[i] += A[i].charAt(j);
            }
            if (isSorted(curr)){
                prev = curr;
            }else{
                res++;
            }
        }
        return res;
    }
    private boolean isSorted(String[] A){
        for (int i = 0; i < A.length - 1; i++){
            if (A[i].compareTo(A[i+1]) > 0){
                return false;
            }
        }
        return true;
    }
}


Method 2: 
Time complexity: O(mn)
Space complexity: O(m)
class Solution {
    public int minDeletionSize(String[] A) {
        int m = A.length;
        int n = A[0].length();
        boolean[] cut = new boolean[m-1];//it means A[i] < A[i+1] at i, in other words, previous columns are already sorted to deal with equality case
        int i = 0;
        int res = 0;
        for (int j = 0; j < n; j++){
            for (i = 0; i < m - 1; i++){
                if (!cut[i] && A[i].charAt(j) > A[i+1].charAt(j)){
                    res++;
                    break;
                }
            }
            //update cut only if the column is selected
            if (i == m -1){
                for (int k = 0; k < m-1; k++){
                    if (A[k].charAt(j) < A[k+1].charAt(j)){
                        cut[k] = true;
                    }
                }
            }
        }
        return res;
    }
}
