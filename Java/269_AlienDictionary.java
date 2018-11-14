 There is a new alien language which uses the latin alphabet. However, the order among 
 letters are unknown to you. You receive a list of non-empty words from the dictionary,
 where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]

The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]

The order is invalid, so return "".

Note:

    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.



Method: Topological sort

First, build a degree map for each character in all the words:

w:0
r:0
t:0
f:0
e:0

Then build the hashmap by comparing the adjacent words, the first character that is different between two adjacent words 
reflect the lexicographical order. For example:

 "wrt",
 "wrf",
    first different character is 3rd letter, so t comes before f

 "wrf",
 "er",
    first different character is 1rd letter, so w comes before e

The characters in set come after the key. x->y means letter x comes before letter y. x -> set: y,z,t,w means x comes 
before all the letters in the set. The final HashMap “map” looks like.

t -> set: f    
w -> set: e
r -> set: t
e -> set: r

and final HashMap “degree” looks like, the number means “how many letters come before the key”:

w:0
r:1
t:1
f:1
e:1

Then use Kahn’s aglorithm to do topological sort. This is essentially BFS.
https://en.wikipedia.org/wiki/Topological_sorting


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0){
            return "";
        }
        Map<Character, Set<Character>> map = new HashMap<>(); //priority map
        Map<Character, Integer> indegree = new HashMap<>(); //indegree map
            
        //1. build indgree map and priority map
        for (String word : words){
            for (char c : word.toCharArray()){
                indegree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++){
            String curr = words[i];
            String next = words[i+1];
            int len = Math.min(curr.length(), next.length());
            for (int j = 0; j < len; j++){
                if (curr.charAt(j) != next.charAt(j)){
                    char c1 = curr.charAt(j);
                    char c2 = next.charAt(j);
                    if (!map.containsKey(c1)){
                        map.put(c1, new HashSet<Character>());
                    }
                    if (!map.get(c1).contains(c2)){
                        map.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        
        //2. topological sort (BFS)
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : indegree.keySet()){
            if (indegree.get(c) == 0){
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()){
            char ch = queue.poll();
            sb.append(ch);
            if (map.containsKey(ch)){
               for (Character c : map.get(ch)){
                    indegree.put(c, indegree.get(c) - 1);
                    if (indegree.get(c) == 0){
                        queue.offer(c);
                    }
               } 
            }
        }
        if (sb.length() != indegree.size()){
            return "";
        }
        return sb.toString();
    }
}
