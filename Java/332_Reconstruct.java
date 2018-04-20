Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> result = new ArrayList<>();
        int steps = tickets.length;
        Map<String, Queue<String>> graph = new HashMap<>();
        for (int i = 0; i < steps; i++){
            if (!graph.containsKey(tickets[i][0])){
                graph.put(tickets[i][0], new PriorityQueue<String>());
            }
            graph.get(tickets[i][0]).add(tickets[i][1]);
        }
        dfs(result, graph, "JFK");
        return result;
    }
    private void dfs(List<String> result, Map<String, Queue<String>> graph, String start){
    //    result.add(start);
        Queue<String> pq = graph.get(start);
        while (pq != null && !pq.isEmpty()){
            String str = pq.poll();
            dfs(result, graph, str);
        }
        result.add(0, start);

    }
}
