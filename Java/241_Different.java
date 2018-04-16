Given a string of numbers and operators, return all possible results from computing all the different possible
ways to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

Method 1: Divide and Conquer
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            if (!Character.isDigit(input.charAt(i))){
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> res1 = diffWaysToCompute(part1);
                List<Integer> res2 = diffWaysToCompute(part2);
                for (int i1 : res1){
                    for(int i2 : res2){
                        char c = input.charAt(i);
                        switch (c){
                            case '+' : 
                                result.add(i1 + i2);
                                break;
                            case '-':
                                result.add(i1 - i2);
                                break;
                            case '*':
                                result.add(i1 * i2);
                                break;
                        }
                    }
                }
            }
        }
        if (result.isEmpty()){
            result.add(Integer.parseInt(input));
        }
        return result;
    }
}

Method 2: Divide and Conquer + Memorization
class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)){
            return map.get(input);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            if (!Character.isDigit(input.charAt(i))){
                String part1 = input.substring(0, i);
                String part2 = input.substring(i+1);
                List<Integer> res1 = diffWaysToCompute(part1);
                List<Integer> res2 = diffWaysToCompute(part2);
                for (int i1 : res1){
                    for(int i2 : res2){
                        char c = input.charAt(i);
                        switch (c){
                            case '+' : 
                                result.add(i1 + i2);
                                break;
                            case '-':
                                result.add(i1 - i2);
                                break;
                            case '*':
                                result.add(i1 * i2);
                                break;
                        }
                    }
                }
            }
        }
        if (result.isEmpty()){
            result.add(Integer.parseInt(input));
        }
        map.put(input, result);
        return result;
    }
}
