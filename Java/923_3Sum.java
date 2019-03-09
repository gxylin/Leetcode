Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and 
A[i] + A[j] + A[k] == target.

As the answer can be very large, return it modulo 10^9 + 7.

 

Example 1:

Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (A[i], A[j], A[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: A = [1,1,2,2,2,2], target = 5
Output: 12
Explanation: 
A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.
 

Note:

3 <= A.length <= 3000
0 <= A[i] <= 100
0 <= target <= 300

Method 1:
Time complexity: O(N^2)
Space complexity: O(N)
class Solution {
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Long> map = new HashMap<>();
        for (int i : A){
            map.put(i, map.getOrDefault(i, 0L) + 1L);
        }
        int n = map.size();
        int[] B = new int[n];
        Arrays.sort(A);
       ///remove duplicates from sorted array
        int index = 0;
        B[0] = A[0];
        for (int i = 0; i < A.length; i++){
            if (B[index] != A[i]){
                index++;
                B[index] = A[i];
            }
        }
     //////////////////////////////////
        int mod = (int)Math.pow(10, 9) + 7;
        long res = 0;
        for (int i = 0; i < n; i++){
            int sumTarget = target - B[i];
            if (sumTarget >= B[i]){
                for (int j = i; j < n; j++){
                    if (sumTarget - B[j] >= B[j] && map.containsKey(sumTarget - B[j])){
                        int first = B[i];
                        int second = B[j];
                        int third = sumTarget - B[j];
                        long num = map.get(second);
                        System.out.println(num);
                        if (first < second && second < third){
                            res = (res + ((map.get(first) * map.get(second)) * map.get(third))%mod)%mod;
                        }else if (first == second && second < third){
                            res = (res + ((num * (num - 1) / 2) % mod * map.get(third))%mod)%mod;  
                        }else if (first < second && second == third){
                            res = (res + (map.get(first) * ((num * (num - 1) / 2)%mod))%mod)%mod;
                        }else if (first == second && second ==  third){
                            res = (res + (num * (num - 1) * (num - 2) / 6)%mod)%mod;
                        }
                    }
                }     
            }
        }
        return (int)res;
    }
}

Method 2:
 Build a map for counting different sums of two numbers. 
class Solution {
    public int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int mod = (int) Math.pow(10, 9) + 7;
        for (int i = 0; i < A.length; i++){
            res = (res + map.getOrDefault(target - A[i], 0)) % mod;
            for (int j = 0; j < i; j++){
                map.put(A[i] + A[j], map.getOrDefault(A[i] + A[j], 0) + 1);
            }
        }
        return res;
    }
}

