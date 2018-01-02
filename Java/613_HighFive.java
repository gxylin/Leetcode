

There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, 
find the average of 5 highest scores for each person.
Have you met this question in a real interview?
Example

Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]

Return 

public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, Double> result = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        if (results == null || results.length == 0) {
            return result;
        }
        for (int i = 0; i < results.length; i++) {
            if (!map.containsKey(results[i].id)) {
                map.put(results[i].id, new PriorityQueue<Integer>(5));
            }
            // map.get(results[i].id).offer(results[i].score);
            // if (map.get(results[i].id).size() > 5) {
            //     map.get(results[i].id).poll();
            // }
            if (map.get(results[i].id).size() < 5) {
                map.get(results[i].id).offer(results[i].score);
            } else {
                if (results[i].score > map.get(results[i].id).peek()) {
                    map.get(results[i].id).poll();
                    map.get(results[i].id).offer(results[i].score);
                }
            }
        }
        for (int key: map.keySet()) {
            int sum = 0;
            for (int val: map.get(key)) {
                sum += val;
            }
            result.put(key, sum / 5.0);
        }
        return result;
    }
 }
