Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2
Note:

A.length <= 2000
A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.

Method 1: Union Find
Time complexity: O(n^2 * m)
class Solution {
    class UF {
        int[] parent;
        int[] size;
        int count;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
            count = N;
        }
        public int find (int x){
            if (parent[x] == x){
                return x;
            }
            return find(parent[x]);
        }
        public void union (int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootY] = rootX;
                size[x] += size[y];
                count--;
            }
        }
    }
    public int numSimilarGroups(String[] A) {
        int N = A.length;
        UF uf = new UF(N);
        for (int i = 0; i < N; i++){
            for (int j = i; j < N; j++){
                if (isSimilar(A[i], A[j])){
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }
    private boolean isSimilar(String a, String b){
        if (a.length() != b.length()){
            return false;
        }
        int n = a.length();
        int firstInd = n;
        int secondInd = n;
        for (int i = 0; i < n; i++){
            if (a.charAt(i) != b.charAt(i)){
                if (firstInd == n){
                    firstInd = i;
                }else if (firstInd != n && secondInd == n){
                    if (a.charAt(firstInd) != b.charAt(i) || a.charAt(i) != b.charAt(firstInd)){
                        return false;
                    }
                    secondInd = i;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
