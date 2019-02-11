Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output
all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".
Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
Note:
The length of both lists will be in the range of [1, 1000].
The length of strings in both lists will be in the range of [1, 30].
The index is starting from 0 to the list length minus 1.
No duplicates in both lists.

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++){
            if (map.containsKey(list2[i])){
                min = Math.min(min, map.get(list2[i]) + i);
            }
        }
        for (int i = 0; i < list2.length; i++){
            if (map.containsKey(list2[i]) && map.get(list2[i]) + i == min){
                list.add(list2[i]);
            }
        }
        String[] res = new String[list.size()];
        int i = 0;
        for (String s: list){
            res[i++] = s;
        }
        return res;
    }
}

public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map = new HashMap<>();
    List<String> res = new LinkedList<>();
    int minSum = Integer.MAX_VALUE;
    for (int i=0;i<list1.length;i++) map.put(list1[i], i);
    for (int i=0;i<list2.length;i++) {
        Integer j = map.get(list2[i]);
        if (j != null && i + j <= minSum) {
            if (i + j < minSum) { res.clear(); minSum = i+j; }
            res.add(list2[i]);
        }
    }
    return res.toArray(new String[res.size()]);
}

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++){
            if (map.containsKey(list2[i])){
                if (map.get(list2[i]) + i < min){
                    min = map.get(list2[i]) + i;
                    res.clear();
                    res.add(list2[i]);
                }else if (map.get(list2[i]) + i == min){
                    res.add(list2[i]);
                }
            }
        }
        String[] result = new String[res.size()];
        for (int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
}
