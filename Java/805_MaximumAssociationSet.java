Amazon sells books, every book has books which are strongly associated with it. 
Given ListA and ListB,indicates that ListA [i] is associated with ListB [i] which represents 
the book and associated books. Output the largest set associated with each other(output in any sort). 
You can assume that there is only one of the largest set.

 Notice
The number of books does not exceed 5000.
Have you met this question in a real interview? 
Example
Given ListA = ["abc","abc","abc"], ListB = ["bcd","acd","def"], return["abc","acd","bcd","dfe"].

Explanation:
abc is associated with bcd, acd, dfe, so the largest set is the set of all books
Given ListA = ["a","b","d","e","f"], ListB = ["b","c","e","g","g"], return ["d","e","f","g"].

Explanation:
The current set are [a, b, c] and [d, e, g, f], then the largest set is [d, e, g, f]

public class Solution {
    /**
     * @param ListA: The relation between ListB's books
     * @param ListB: The relation between ListA's books
     * @return: The answer
     */
    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        Map<String,Set<String>> map = new HashMap<>();
        for(int i=0;i<ListA.length;i++){
            if(!map.containsKey(ListA[i])){
                map.put(ListA[i],new HashSet<>());
            }
            if(!map.containsKey(ListB[i])){
                map.put(ListB[i],new HashSet<>());
            }
            if(!map.get(ListA[i]).contains(ListB[i]))
                map.get(ListA[i]).add(ListB[i]);
            if(!map.get(ListB[i]).contains(ListA[i]))
                map.get(ListB[i]).add(ListA[i]);
        }

        List<String> res = new ArrayList<>();
        Set<String> cur = new HashSet<>();
        for(String str:ListA){
            if(map.containsKey(str)) {
                helper(cur, str, map);
                if (cur.size() > res.size()) {
                    res.clear();
                    res.addAll(cur);
                }
                cur.clear();
            }
        }
        return res;
    }

    private static void helper(Set<String> cur, String str, Map<String,Set<String>> map){
        cur.add(str);
        if(map.containsKey(str)) {
            Set<String> next = map.get(str);
            map.remove(str);
            for(String s:next){
                helper(cur,s,map);
            }
        }
    }
}
