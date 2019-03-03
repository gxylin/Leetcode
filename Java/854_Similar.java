Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so
that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

Classic BFS: swap only pair at every step and use bfs to guaranteee shortest path
find the first mismatch character at j , then find the second one at k, only swap when 
curr.charAt(k) == B.charAt(j) && curr.charAt(k) != B.charAt(k)

Method 1: BFS
class Solution {
    public int kSimilarity(String A, String B) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer(A);
        seen.add(A);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String curr = queue.poll();
                if (curr.equals(B)){
                    return res;
                }
                int j = 0;
                while (j < curr.length() && curr.charAt(j) == B.charAt(j)){
                    j++;
                }
                for (int k = j + 1; k < curr.length(); k++){
                    if (curr.charAt(k) == B.charAt(j) && curr.charAt(k) != B.charAt(k)){//this is the key
                        String next = swap(curr, j, k);//return string to ensure that curr won't change
                        if (!seen.contains(next)){
                            queue.offer(next);
                            seen.add(next);
                        }
                    }
                }
            }
            res++;
        }
        return res;
    }
    private String swap(String curr, int j, int k){
        char[] arr = curr.toCharArray();
        char temp = arr[j];
        arr[j] = arr[k];
        arr[k] = temp;
        return new String(arr);
    }
}

Method 2: DFS + memo
class Solution {
    public int kSimilarity(String A, String B) {
        Map<String, Integer> memo = new HashMap<>();
        return minStep(A.toCharArray(), B, memo, 0);
    }
    private int minStep(char[] A, String B, Map<String, Integer> memo, int i){
        String sa = new String(A);
        if (sa.equals(B)){
            return 0;
        }
        if (memo.containsKey(sa)){
            return memo.get(sa);
        }
        
        while (i < sa.length() && A[i] == B.charAt(i)){
            i++;
        }
        int min = Integer.MAX_VALUE;
        for (int j = i + 1; j < sa.length(); j++){
            if (sa.charAt(j) == B.charAt(i)&& sa.charAt(j) != B.charAt(j)){
                swap(A, i, j);
                int next = minStep(A, B, memo, i+1);
                if (next != Integer.MAX_VALUE){
                    min = Math.min(min, next + 1);
                }
                swap(A, i, j);
            }
        }
        memo.put(sa, min);
        return min;
    }
     private void swap(char[] cs, int i, int j) {
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
