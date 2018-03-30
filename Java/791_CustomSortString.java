S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they 
match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur
before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid 

class Solution {
    class Pair{
        char c;
        int val;
        public Pair(char c, int val){
            this.c = c;
            this.val = val;
        }
    }
    public String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), i);
        }
        StringBuilder sb = new StringBuilder();
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.val - b.val;
            }
        });
        for (int i = 0; i < T.length(); i++){
            char c = T.charAt(i);
            pq.offer(new Pair(c, map.getOrDefault(c, 26)));
        }
        while (!pq.isEmpty()){
            sb.append(pq.poll().c);
        }
        return sb.toString();
    }
}
