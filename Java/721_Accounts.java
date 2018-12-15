Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that 
is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people 
could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and 
the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", 
"john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

Method 1: DFS
Intuition

Draw an edge between two emails if they occur in the same account. The problem comes down to finding connected components of this graph.

Algorithm

For each account, draw the edge from the first email to all other emails. Additionally, we'll remember a map from emails to 
names on the side. After finding each connected component using a depth-first search, we'll add that to our answer.

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //build graph
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        for (List<String> account : accounts){
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++){
                if (!graph.containsKey(account.get(i))){
                    graph.put(account.get(i), new HashSet<>());
                }
                nameMap.put(account.get(i), name);
                if (i == 1){
                    continue;
                }
                graph.get(account.get(i)).add(account.get(i-1));
                graph.get(account.get(i-1)).add(account.get(i));
            }
        }
        //dfs search
        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String key : nameMap.keySet()){
            List<String> list = new ArrayList<>();
            if (!visited.contains(key)){
                dfs(res, list, graph, visited, key);
                Collections.sort(list);
                list.add(0, nameMap.get(key));
                res.add(list);
            }   
        }
        return res;
    }
    private void dfs(List<List<String>> res, List<String> list, Map<String, Set<String>> graph, Set<String> visited, String key){
        visited.add(key);
        list.add(key);
        for (String nei : graph.get(key)){
            if (!visited.contains(nei)){
                dfs(res, list, graph, visited, nei); 
            }
        }
    }
}

Method 2: Union Find
class Solution {
    class UnionFind{
        int[] parent;
        public UnionFind(){
            int N = 1000 * 10;
            parent = new int[N];
            for (int i = 0; i < N; i++){
                parent[i] = i;
            }
        }
        public int find(int x){
            if (parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootY] = rootX;
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, String> emailToNameMap = new HashMap<>();
        Map<String, Integer> emailToIDMap = new HashMap<>();
        UnionFind uf = new UnionFind();
        //construct union find
        int id = 0;
        for (List<String> account : accounts){
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++){
                if (!emailToIDMap.containsKey(account.get(i))){
                    emailToIDMap.put(account.get(i), id++);
                }
                uf.union(emailToIDMap.get(account.get(1)), emailToIDMap.get(account.get(i)));
                emailToNameMap.put(account.get(i), name);
            }
        }
        //transfer the element in union find to HashMap
        Map<Integer, List<String>> graph = new HashMap<>();
        for (String email : emailToNameMap.keySet()){
            int p = uf.find(emailToIDMap.get(email));
            if (!graph.containsKey(p)){
                graph.put(p, new ArrayList<>());
            }
            graph.get(p).add(email);
        }
        for (int parent : graph.keySet()){
            List<String> list = graph.get(parent);
            Collections.sort(list);
            String name = list.get(0);
            list.add(0, emailToNameMap.get(name));
            res.add(list);
        }
        return res;
    }
}
