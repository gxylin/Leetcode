Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

class Solution {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0){
            return s;
        }
        int[] hash = new int[256];
        for (int i = 0; i < s.length(); i++){
            hash[s.charAt(i)]++;
        }
        TreeMap<Integer, List<Character>> map = new TreeMap<Integer, List<Character>>();
        for (int i = 0 ; i < hash.length; i++){
            if (hash[i] > 0){
                if (!map.containsKey(hash[i])){
                    map.put(hash[i], new ArrayList<Character>());
                }
                map.get(hash[i]).add((char)i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (map.size() > 0){
            Map.Entry<Integer, List<Character>> entry = map.pollLastEntry();
            for (Character c : entry.getValue()){
                for (int i = 0; i < entry.getKey(); i++){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}

Method 2: PQ
class Solution {
    class Pair{
        char c;
        int freq;
        public Pair(char c, int freq){
            this.c = c;
            this.freq = freq;
        }
    }
    public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        if (s == null || s.length() == 0){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Queue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return b.freq - a.freq;
            }
        });
        for (Character ch : map.keySet()){
            queue.offer(new Pair(ch, map.get(ch)));
        }
        while (!queue.isEmpty()){
            Pair p = queue.poll();
            int count = p.freq;
            char c = p.c;
            while (count > 0){
                sb.append(c);
                count--;
            }
        }
        return sb.toString();
    }
}
