We have an array A of integers, and an array queries of queries.

For the i-th query val = queries[i][0], index = queries[i][1], we add val to A[index].  Then, the answer to the i-th query is the sum of the even values of A.

(Here, the given index = queries[i][1] is a 0-based index, and each query permanently modifies the array A.)

Return the answer to all queries.  Your answer array should have answer[i] as the answer to the i-th query.

 

Example 1:

Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
Output: [8,6,2,4]
Explanation: 
At the beginning, the array is [1,2,3,4].
After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
After adding -3 to A[1], the array is [2,-1,3,4], and the sum of even values is 2 + 4 = 6.
After adding -4 to A[0], the array is [-2,-1,3,4], and the sum of even values is -2 + 4 = 2.
After adding 2 to A[3], the array is [-2,-1,3,6], and the sum of even values is -2 + 6 = 4.
 

Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
1 <= queries.length <= 10000
-10000 <= queries[i][0] <= 10000
0 <= queries[i][1] < A.length


Method 1: 
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int n = A.length;
        int[] res = new int[queries.length];
        int sum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++){
            if (A[i] % 2 == 0){
                sum += A[i];
                set.add(i);
            }
        }
        int j = 0;
        res[0] = sum;
        System.out.println(sum);
        for (int[] query : queries){
            int index = query[1];
            int val = query[0];
            if ((A[index] + val) % 2 == 0){
                if (set.contains(index)){
                    sum += val;
                    res[j++] = sum;
                }else{
                    sum += A[index] + val;
                    res[j++] = sum;
                    set.add(index);
                }
            }else{
                if (set.contains(index)){
                    sum -= A[index];
                    res[j++] = sum;
                    set.remove(index);
                }else{
                    res[j++] = sum;
                }
            }
            A[index] += val;
        }
        return res;
    }
}

Method 2: Best
Time complexity: O(N)
Space complexity: O(1)

class Solution {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int n = A.length;
        int[] res = new int[queries.length];
        int sum = 0;
        for (int i = 0; i < n; i++){
            if (A[i] % 2 == 0){
                sum += A[i];
            }
        }
        int i = 0;
        for (int[] query : queries){
            int val = query[0];
            int index = query[1];
            if (A[index] % 2 == 0){
                sum -= A[index];
            }
            A[index] += val;
            if (A[index] % 2 == 0){
                sum += A[index];
            }
            res[i++] = sum;
        }
        return res;
    }
}
