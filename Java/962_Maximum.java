Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.

Find the maximum width of a ramp in A.  If one doesn't exist, return 0.

 

Example 1:

Input: [6,0,8,2,1,5]
Output: 4
Explanation: 
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
Example 2:

Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation: 
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 

Note:

2 <= A.length <= 50000
0 <= A[i] <= 50000


Method 1: Brute force (TLE)
Time complexity: O(N^2)
Space complexity: O(1)
class Solution {
    public int maxWidthRamp(int[] A) {
        int n = A.length;
        int res = -1;
        for (int i = 0; i < n - 1; i++){
            for (int j = n-1; j > i; j--){
                if (A[i] <= A[j]){
                    res = Math.max(res, j - i);
                }
            }
        }
        return res == -1 ? 0 : res;
    }
}

Method 2: sorting
Time complexity: O(NlogN)
Space complexity: O(N)
class Solution {
    class Pair {
        int index;
        int val;
        public Pair (int index, int val){
            this.index = index;
            this.val = val;
        }
    }
    public int maxWidthRamp(int[] A) {
        int N = A.length;
        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++){
            pairs[i] = new Pair(i, A[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.val - p2.val;
            }
        });
        
        //below is the code: for given array, find the largest difference between current element and previous elements
        int res = 0;
        int min = N;
        for (int i = 0; i < N; i++){
            res = Math.max(res, pairs[i].index - min);
            min = Math.min(min, pairs[i].index);
        }
        return res;
    }
}


Method 3: best solution
class Solution {
    public int maxWidthRamp(int[] A) {
        int N = A.length;
        int[] maxR = new int[N];
        int[] minL = new int[N];
        minL[0] = A[0];
        for (int i = 1; i < N; i++){
            minL[i] = Math.min(A[i], minL[i-1]);
        } 
        maxR[N-1] = A[N-1];
        for (int j = N-2; j >= 0; j--){
            maxR[j] = Math.max(A[j], maxR[j+1]);
        }
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < N && j < N){
            if (minL[i] <= maxR[j]){
                res = Math.max(res, j - i);
                j++;
            }else{
                i++;
            }
        }
        return res;
    }
}
https://leetcode.com/problems/maximum-width-ramp/discuss/208341/O(N)-JAVA
Actually, no need minL
class Solution {
    public int maxWidthRamp(int[] A) {
        int N = A.length;
        int[] maxR = new int[N];
        maxR[N-1] = A[N-1];
        for (int j = N-2; j >= 0; j--){
            maxR[j] = Math.max(A[j], maxR[j+1]);
        }
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < N && j < N){
            if (A[i] <= maxR[j]){
                res = Math.max(res, j - i);
                j++;
            }else{
                i++;
            }
        }
        return res;
    }
}

Method 4: monotonic decreasing stack store index
https://leetcode.com/problems/maximum-width-ramp/discuss/208348/JavaC++Python-O(N)-Using-Stack
Time complexity: O(N)
class Solution {
    public int maxWidthRamp(int[] A) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++){
            if(stack.isEmpty() || A[stack.peek()] >= A[i]){
                stack.push(i);
            }
        }
        int res = 0;
        for (int i = A.length - 1; i > 0; i--){
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]){
                res = Math.max(res, i - stack.pop());
            }
            if (stack.isEmpty()){
                break;
            }
        }
        return res;
    }
}

