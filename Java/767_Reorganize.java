Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].


class Solution {
    class Pair {
        char ch;
        int num;
        public Pair (char ch, int num){
            this.ch = ch;
            this.num = num;
        }
    }
    public String reorganizeString(String S) {
        PriorityQueue<Pair> maxPQ = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                if (p1.ch == p2.ch){
                    return p1.ch - p2.ch;
                }
                return p2.num - p1.num;
            }
        });
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++){
            map.put(S.charAt(i), map.getOrDefault(S.charAt(i), 0) + 1);
            if (map.get(S.charAt(i)) > (S.length() + 1) / 2) return "";
        }
        for (char key : map.keySet()){
            maxPQ.offer(new Pair(key, map.get(key)));
        }
        StringBuilder sb = new StringBuilder();
        while (!maxPQ.isEmpty()){
            Pair first = maxPQ.poll();
            if (sb.length() == 0 || first.ch != sb.charAt(sb.length() - 1)){
                sb.append(first.ch);
                if (first.num > 1){
                    maxPQ.offer(new Pair(first.ch, first.num - 1));
                }
            }else{
                Pair second = maxPQ.poll();
                sb.append(second.ch);
                if (second.num > 1){
                    maxPQ.offer(new Pair(second.ch, second.num - 1));
                }
                maxPQ.offer(new Pair(first.ch, first.num));
            }
        }
        return sb.toString();
    }
}
