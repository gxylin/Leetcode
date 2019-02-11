In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
Example 2:
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
Note:
There are at most 6 kinds of items, 100 special offers.
For each item, you need to buy at most 6 of them.
You are not allowed to buy more items than you want, even if that would lower the overall price.

    
Method: best solution:
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int res = Integer.MAX_VALUE;
        for (List<Integer> list : special){
            boolean pass = true;
            for (int i = 0; i < n; i++){
                if (list.get(i) > needs.get(i)){
                    pass = false;
                    break;
                }
            }
            if (pass){
                List<Integer> newNeed = new ArrayList<>();
                for (int i = 0; i < n; i++){
                    newNeed.add(needs.get(i) - list.get(i));
                }
                res = Math.min(res, shoppingOffers(price, special, newNeed) + list.get(list.size() - 1));
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += price.get(i) * needs.get(i);
        }
        res = Math.min(res, sum);
        return res;
    }
}

With memo
class Solution {
    Map<String, Integer> map = new HashMap<>();
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        StringBuilder sb = new StringBuilder();
        for (int i : needs){
            sb.append(i + ":");
        }
        String key = sb.toString();
        if (map.containsKey(key)){
            return map.get(key);
        }
        int n = price.size();
        int res = Integer.MAX_VALUE;
        for (List<Integer> list : special){
            boolean pass = true;
            for (int i = 0; i < n; i++){
                if (list.get(i) > needs.get(i)){
                    pass = false;
                    break;
                }
            }
            if (pass){
                List<Integer> newNeed = new ArrayList<>();
                for (int i = 0; i < n; i++){
                    newNeed.add(needs.get(i) - list.get(i));
                }
                res = Math.min(res, shoppingOffers(price, special, newNeed) + list.get(list.size() - 1));
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++){
            sum += price.get(i) * needs.get(i);
        }
        res = Math.min(res, sum);
        map.put(key, res);
        return res;
    }
}


Method: DFS + memo
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        return dfs(price, special, needs, map);
    }
    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map){
        if (map.containsKey(needs)){
            return map.get(needs);
        }
        int res = dot(price, needs);
        for (int i = 0; i < special.size(); i++){
            int j = 0;
            List<Integer> clone = new ArrayList<Integer>(needs);
            for (j = 0; j < needs.size(); j++){
                if (clone.get(j) < special.get(i).get(j)){
                    break;
                }
                clone.set(j, clone.get(j) - special.get(i).get(j));
            }
            if (j == needs.size()){
                res = Math.min(res, special.get(i).get(j) + dfs(price, special, clone, map));
            }
        }
        map.put(needs, res);
        return res;
    }
    private int dot(List<Integer> price, List<Integer> needs){
        int sum = 0;
        for (int i = 0; i < price.size(); i++){
            sum += price.get(i) * needs.get(i);
        }
        return sum;
    }
}

Method 
backtracking: TLE
class Solution {
    int min = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        for (int i = 0; i < price.size(); i++){
            List<Integer> temp  = new ArrayList<>();
            for (int j = 0; j < price.size() + 1; j++){
                if (i == j){
                    temp.add(1);
                }else if (j == price.size()){
                    temp.add(price.get(i));
                }else{
                    temp.add(0);
                }
            }
            special.add(temp);
        }
        dfs(special, needs, new ArrayList<>(), 0);
        return min;
    }
    private void dfs(List<List<Integer>> special, List<Integer> needs, List<Integer> candidate, int sum){
        if (match(special, needs, candidate)){
            min = Math.min(min, sum);
            return;
        }
        for (int i = 0; i < special.size(); i++){
            boolean useSpecial = true;
            for (int j = 0; j < needs.size(); j++){
                if (needs.get(j) < special.get(i).get(j)){
                    useSpecial = false;
                    break;
                }
            }
            if (useSpecial){
                candidate.add(i);
                dfs(special, needs, candidate, sum + special.get(i).get(needs.size()));
                candidate.remove(candidate.size() - 1);
            }
        }
    }
    private boolean match(List<List<Integer>> special, List<Integer> needs, List<Integer> candidate){
        for (int i = 0; i < needs.size(); i++){
            int sum = 0;
            for (int j = 0; j < candidate.size(); j++){
                sum +=  special.get(candidate.get(j)).get(i);
            }
            if (needs.get(i) != sum){
                return false;
            }
        }
        return true;
    }
}
