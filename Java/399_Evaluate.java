Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

Method: Graph
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int N = values.length;
        double[] res = new double[queries.length];
        Map<String, Map<String, Double>> graph = new HashMap<>();
        //build graph
        for (int i = 0; i < N; i++){
            String[] equation = equations[i];
            String dividend = equation[0];
            String divisor = equation[1];
            if (!graph.containsKey(dividend)){
                graph.put(dividend, new HashMap<String, Double>());
                graph.get(dividend).put(dividend, 1.0);
            }
            graph.get(dividend).put(divisor, values[i]);
            if (!graph.containsKey(divisor)){
                graph.put(divisor, new HashMap<String, Double>());
                graph.get(divisor).put(divisor, 1.0);
            }
            graph.get(divisor).put(dividend, 1.0/values[i]);
        }
        //dfs to calculate queries
        for (int i = 0; i < queries.length; i++){
            String[] query = queries[i];
            String dividend = query[0];
            String divisor = query[1];
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)){
                res[i] = -1.0;
            }else{
                res[i] = dfs(graph, dividend, divisor, new HashSet<String>());
            }
        }
        return res;
    }
    private double dfs(Map<String, Map<String, Double>> graph, String dividend, String divisor,  Set<String> seen){
        if (dividend.equals(divisor)){
            return 1.0;
        }
        seen.add(dividend);
        Map<String, Double> map = graph.get(dividend);
        for (String key : map.keySet()){
            if (!seen.contains(key)){
                double val =  dfs(graph, key, divisor, seen);
                if (val > 0){
                    return map.get(key) * val;
                }
            }
        }
        return -1.0;
    }
}


class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int N = queries.length;
        //build graph
        double[] res = new double[N];
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++){
            String dividend = equations[i][0];
            String divisor = equations[i][1];
            if (!graph.containsKey(dividend)){
                graph.put(dividend, new HashMap<>());
                graph.get(dividend).put(dividend, 1.0);
            }
            graph.get(dividend).put(divisor, values[i]);
            if (!graph.containsKey(divisor)){
                graph.put(divisor, new HashMap<>());
                graph.get(divisor).put(divisor, 1.0);
            }
            graph.get(divisor).put(dividend, 1.0/values[i]);
        }
        //dfs
        for (int i = 0; i < N; i++){
            String dividend = queries[i][0];
            String divisor = queries[i][1];
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)){
                res[i] = -1.0;
            }else{
                Set<String> seen = new HashSet<>();
                seen.add(dividend);
                res[i] = dfs(graph, dividend, divisor, seen);
            }   
        }
        return res;
    }
    private double dfs(Map<String, Map<String, Double>> graph, String dividend, String divisor, Set<String> seen){
        if (dividend.equals(divisor)){
            return 1.0;
        }
        Map<String, Double> map = graph.get(dividend);
        for (String key : map.keySet()){
            if (!seen.contains(key)){
                seen.add(key);
                double val = dfs(graph, key, divisor, seen);
                if (val > 0){
                    return map.get(key) * val;
                }
            }
        }
        return -1.0;
    }
}

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int N = queries.length;
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < values.length; i++){
            String dividend = equations[i][0];
            String divisor = equations[i][1];
            if (!graph.containsKey(dividend)){
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)){
                graph.put(divisor, new HashMap<>());
            }
            graph.get(dividend).put(divisor, values[i]);
            graph.get(divisor).put(dividend, 1.0 / values[i]);
        }
        double[] res = new double[N];
        for (int i = 0; i < N; i++){
            String dividend = queries[i][0];
            String divisor = queries[i][1];
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)){
                res[i] = -1.0;
            }else{
                Set<String> seen = new HashSet<>();
                res[i] = dfs(graph, dividend, divisor, seen);
            }
        }
        return res;
    }
    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> seen){
        if (start.equals(end)){
            return 1.0;
        }
        seen.add(start);
        Map<String, Double> map = graph.get(start);
        for (String next : map.keySet()){
            if (!seen.contains(next)){
                double val = dfs(graph, next, end, seen);
                if (val > 0){
                    return map.get(next) * val;
                }
            }  
        }
        return -1.0; 
    }
}
