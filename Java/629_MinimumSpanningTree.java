Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find some edges, connect all the cities and spend the least amount.
Return the connects if can connect all the cities, otherwise return empty list.

 Notice

Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.

Have you met this question in a real interview? Yes
Example
Gievn the connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]

Return ["Acity","Bcity",1], ["Acity","Ccity",2]


Method: Union Find + Kruskal


/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    class UFS{
        int[] f;
        
        public UFS(int n){
            f = new int[n];
            for (int i = 0; i < n; i++){
                f[i] = -1;
            }
        }
        public int find(int x){
            if (f[x] < 0){
                return x;
            }
            f[x] = find(f[x]);
            return f[x];
        }
        public void union(int a, int b){
            a = find(a);
            b = find(b);
            if (f[a] < f[b]){
                f[a] += f[b];
                f[b] = a;
            }else{
                f[b] += f[a];
                f[a] = b;
            }
        }
    }
    Map<String, Integer> name2ID = new HashMap<>();
    int n = 0;
    public int getID(String name){
        if (name2ID.containsKey(name)){
            return name2ID.get(name);
        }else{
            name2ID.put(name, n++);
            return n - 1;
        }
    }
    public List<Connection> lowestCost(List<Connection> connections) {
        Collections.sort(connections, new Comparator<Connection>(){
            public int compare(Connection a, Connection b){
                if (a.cost != b.cost){
                    return a.cost - b.cost;
                }else if (!a.city1.equals(b.city1)){
                    return a.city1.compareTo(b.city1); 
                }
                return a.city2.compareTo(b.city2);
            }
        });
        List<Connection> result = new ArrayList<>();
        UFS ufs = new UFS(connections.size() * 2);
        
        for (Connection conn : connections){
            int id1 = getID(conn.city1);
            int id2 = getID(conn.city2);
            if (ufs.find(id1) != ufs.find(id2)){
                result.add(conn);
                ufs.union(id1, id2);
            }
        }
        if (result.size() == n - 1){
            return result;
        }
        return new ArrayList<>();
    }
}
