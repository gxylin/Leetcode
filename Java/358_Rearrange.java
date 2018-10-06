Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.

Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.

Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.

Method 1: PQ + Greedy
Time complexity: O(N*logN) N is the s.length()
class Solution {
    class Pair {
        char ch;
        int num;
        public Pair (char ch, int num){
            this.ch = ch;
            this.num = num;
        }
    }
    public String rearrangeString(String s, int k) {
        if (k == 0){
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Pair> maxPQ = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p2.num - p1.num;
            }
        });
        for (char c : map.keySet()){
            maxPQ.offer(new Pair(c, map.get(c)));
        }
        StringBuilder sb = new StringBuilder();
        int[] hash = new int[26];
        while (!maxPQ.isEmpty()){
            Pair curr = maxPQ.poll();
            char c = curr.ch;
            int num = curr.num;
            if (sb.length() >= k){
                hash[sb.charAt(sb.length()-k) - 'a']--;
            }
            if (hash[c - 'a'] == 0){
                hash[c - 'a']++;
                sb.append(c);
                num--;
                if (num > 0){
                    maxPQ.offer(new Pair(c, num));
                }
            }else{
                Set<Pair> tmp = new HashSet<>();
                tmp.add(curr);
                while (!maxPQ.isEmpty()){
                    Pair p = maxPQ.poll();
                    if (hash[p.ch - 'a'] == 0){
                        hash[p.ch - 'a']++;
                        sb.append(p.ch);
                        p.num--;
                        if (p.num > 0){
                            maxPQ.offer(new Pair(p.ch, p.num));
                        }
                        for (Pair t : tmp){
                            maxPQ.offer(new Pair(t.ch, t.num));
                        }
                        break;
                    }else{
                        tmp.add(p);
                    }
                }
                if (maxPQ.isEmpty()){
                    return "";
                }
            }
            
        }
        return sb.toString();
    }
}

Method 2: Better version
class Solution {
    class Pair {
        char ch;
        int num;
        public Pair (char ch, int num){
            this.ch = ch;
            this.num = num;
        }
    }
    public String rearrangeString(String s, int k) {
        if (k == 0){
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Pair> maxPQ = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p2.num - p1.num;
            }
        });
        for (char c : map.keySet()){
            maxPQ.offer(new Pair(c, map.get(c)));
        }
        StringBuilder sb = new StringBuilder();
        Queue<Pair> waitQ = new LinkedList<>();
        while (!maxPQ.isEmpty()){
            Pair curr = maxPQ.poll();
            char c = curr.ch;
            int num = curr.num;
            sb.append(c);
            waitQ.offer(new Pair(c, num - 1));
            if (waitQ.size() < k){
                continue;
            }
            Pair p = waitQ.poll();
            if (p.num > 0){
                maxPQ.offer(new Pair(p.ch, p.num));
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
