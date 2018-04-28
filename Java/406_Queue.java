Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers
(h, k), where h is the height of the person and k is the number of people in front of this person who have 
a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89350/Java-solution-using-Arrays.sort()-and-%22insert-sorting%22-idea
Method 1: O(nlogn)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if (a[0] == b[0]){
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            }
        });
        List<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < people.length; i++){
            tmp.add(people[i][1], new int[]{people[i][0], people[i][1]});
        }
        int[][] result = new int[people.length][2];
        for (int i = 0; i < people.length; i++){
            result[i][0] = tmp.get(i)[0];
            result[i][1] = tmp.get(i)[1];
        }
        // int i = 0;
        // for (int[] t : tmp){
        //     result[i][0] = t[0];
        //     result[i++][1] = t[1];
        // }
        
        //return tmp.toArray(new int[people.length][2]);
        return result;
    }
}

