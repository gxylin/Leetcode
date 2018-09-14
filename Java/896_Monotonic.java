An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

 

Example 1:

Input: [1,2,2,3]
Output: true
Example 2:

Input: [6,5,4,4]
Output: true
Example 3:

Input: [1,3,2]
Output: false
Example 4:

Input: [1,2,4,5]
Output: true
Example 5:

Input: [1,1,1]
Output: true
 

Note:

1 <= A.length <= 50000
-100000 <= A[i] <= 100000

Method 1: one pass
class Solution {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1){
            return true;
        }
        boolean isFlat = true;
        boolean increase = false;
        for (int i = 1; i < A.length; i++){
            if (isFlat){
                if (A[i] > A[i-1]){
                    increase = true;
                    isFlat =  false;
                }else if (A[i] < A[i-1]){
                    increase = false;
                    isFlat =  false;
                }
            }else{
                 if (increase && A[i] < A[i-1] || !increase && A[i] > A[i-1]){
                    return false;
                } 
            }
        }
        return true;
    }
}

class Solution {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1){
            return true;
        }
        boolean increase = true;
        boolean decrease = true;
        for (int i = 1; i < A.length; i++){
            increase &= A[i] >= A[i-1];
            decrease &= A[i] <= A[i-1];
        }
        return increase || decrease;
    }
}

Method 2: two pass
class Solution {
    public boolean isMonotonic(int[] A) {
        return increasing(A) || decreasing(A);
    }

    public boolean increasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i)
            if (A[i] > A[i+1]) return false;
        return true;
    }

    public boolean decreasing(int[] A) {
        for (int i = 0; i < A.length - 1; ++i)
            if (A[i] < A[i+1]) return false;
        return true;
    }
}
