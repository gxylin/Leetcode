Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation
of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in 
seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence 
that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].

Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true

The same as Alien Dictionary, build indegree map and priority map
https://github.com/optimisea/Leetcode/blob/master/Java/269_AlienDictionary.java

indegree: Map<Integer, Integer> indegree, which stores the count of numbers that have higher level of the key
priority map: Map<Integer, Set<Integer>> map, which stores the numbers that have lower level of the key

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        Map<Integer, Integer> indegree = new HashMap<>(); //store the number of upper level
        Map<Integer, Set<Integer>> map = new HashMap<>();//store the value of lower level
        for (List<Integer> seq : seqs){
            for (int i = 0; i < seq.size() - 1; i++){
                int curr = seq.get(i);
                int next = seq.get(i+1);
                if (!map.containsKey(curr)){
                    map.put(curr, new HashSet<>());
                }
                Set<Integer> set = map.get(curr);
                if (!set.contains(next)){
                    set.add(next);
                    indegree.put(next, indegree.getOrDefault(next, 0) + 1);
                }
                if (!indegree.containsKey(curr)){
                    indegree.put(curr, 0);
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()){
            if (indegree.get(key) == 0){
                queue.offer(key);
            }
        }
        if (queue.size() > 1){
            return false;
        }
        int index = 1;
        while (!queue.isEmpty()){
            int curr = queue.poll();
            Set<Integer> set = map.get(curr);
            if (set == null){
                continue;
            }
            boolean  hasOne= false;
            for (int next : set){
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0){
                    if (!hasOne){
                        queue.offer(next);
                        index++;
                        hasOne = true;
                    }else{
                        return false;
                    }
                }
            }
        }
        return index == n;
    }
}
