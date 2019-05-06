We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], 
this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number 
of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Queue<Integer> queue = new LinkedList<>();//bus stops
        Set<Integer> visited = new HashSet<>();//bus stops
        Map<Integer, Set<Integer>> graph = new HashMap<>();//key: bus stop, value: buses that pass the bus stop
        for (int i = 0; i < routes.length; i++){
            for (int j = 0; j < routes[i].length; j++){
                if (!graph.containsKey(routes[i][j])){
                    graph.put(routes[i][j], new HashSet<Integer>());
                }
                Set<Integer> set = graph.get(routes[i][j]);
                set.add(i);
            }
        }
        queue.offer(S);
        visited.add(S);//record stop, not bus
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int stop = queue.poll();
                if (stop == T){
                    return count;
                }
                Set<Integer> buses = graph.get(stop);
                for (int bus : buses){
                    for (int j = 0; j < routes[bus].length; j++){
                        if (!visited.contains(routes[bus][j])){
                            visited.add(routes[bus][j]);
                            queue.offer(routes[bus][j]);
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }
}



Method 2: Faster
Use bus as the visited set instead of stopo as the visited set

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T){
            return 0;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();//key : stop, values are the set of buses
        for (int i = 0; i < routes.length; i++){
            for (int j = 0; j < routes[i].length; j++){
                if (!graph.containsKey(routes[i][j])){
                    graph.put(routes[i][j], new HashSet<>());
                }
                graph.get(routes[i][j]).add(i);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>(); 
        Set<Integer> set = new HashSet<>();  //record bus not stop, so we don't do set.add(S)
        queue.offer(S);
        int res = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++){
                int stop = queue.poll();
                Set<Integer> buses = graph.get(stop);
                for (int bus : buses){
                    if (set.contains(bus)){
                        continue;
                    }
                    set.add(bus);
                    for (int j = 0; j < routes[bus].length; j++){
                        if (routes[bus][j] == T){
                            return res;
                        }
                        queue.offer(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
    }
}
