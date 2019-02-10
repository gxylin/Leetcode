For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000
 

Note：

1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
If A.length > 1, then A[0] != 0

Best solution:
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; i--){
            res.add(0, (A[i] + K) % 10);
            K = (A[i] + K) / 10;
        }
        while (K > 0){
            res.add(0, K%10);
            K /= 10;
        }
        return res;
    }
}



class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        List<Integer> klist = new ArrayList<>();
        while (K > 0){
            klist.add(0, K%10);
            K /= 10;
        }
        int i = A.length - 1; 
        int j = klist.size() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0){
            int a = i >= 0 ? A[i] : 0;
            int b = j >= 0 ? klist.get(j) : 0;
            int val = a + b + carry;
            res.add(val%10);
            carry = val/10;
            i--;
            j--;
        }
        List<Integer> result = new ArrayList<>();
        if (carry > 0){
            result.add(carry);
        }
        int n = res.size();
        for (int k = 0; k < n; k++){
            result.add(res.get(n-1-k));
        }
        return result;
    }
}
