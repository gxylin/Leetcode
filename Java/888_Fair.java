Alice and Bob have candy bars of different sizes: A[i] is the size of the i-th bar of candy that Alice has, and B[j] is the size of 
the j-th bar of candy that Bob has.

Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total 
amount of candy.  (The total amount of candy a person has is the sum of the sizes of candy bars they have.)

Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange, and ans[1] is the size of the
candy bar that Bob must exchange.

If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.

 

Example 1:

Input: A = [1,1], B = [2,2]
Output: [1,2]
Example 2:

Input: A = [1,2], B = [2,3]
Output: [1,2]
Example 3:

Input: A = [2], B = [1,3]
Output: [2,3]
Example 4:

Input: A = [1,2,5], B = [2,4]
Output: [5,4]
 

Note:

1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
It is guaranteed that Alice and Bob have different total amounts of candy.
It is guaranteed there exists an answer.


Method 1:
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] res = new int[2];
        int sumA = 0;
        Set<Integer> setA = new HashSet<>();
        for (int i : A){
            sumA += i;
            setA.add(i);
        }
        int sumB = 0;
        Set<Integer> setB = new HashSet<>();
        for (int i : B){
            sumB += i;
            setB.add(i);
        }
        int avg = (sumA + sumB)/2;
        if (sumA > avg){
            for (int i : A){
                if (setB.contains(i-(sumA-avg))){
                    res[0] = i;
                    res[1] = i - (sumA-avg);
                    break;
                }
            }
        }else if (sumA < avg){
            for (int i : A){
                if (setB.contains(i+(avg-sumA))){
                    res[0] = i;
                    res[1] = i + (avg - sumA);
                    break;
                }
            }
        }else{
            res[0] = 0;
            res[1] = 0;
        }
        return res;
    }
}

Method 2: Better solution
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] res = new int[2];
        int sumA = 0;
        Set<Integer> setA = new HashSet<>();
        for (int i : A){
            sumA += i;
            setA.add(i);
        }
        int sumB = 0;
        for (int i : B){
            sumB += i;
        }
        int diff = (sumA - sumB)/2;
        for (int i : B){
            if (setA.contains(i+diff)){
                res[0] = i + diff;
                res[1] = i;
                return res;
            }
        }
        return new int[0];
    }
}

class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] res = new int[2];
        Set<Integer> setA = new HashSet<>();
        int sumA = 0;
        for (int i : A){
            sumA += i;
            setA.add(i);
        }
        int sumB = 0;
        Set<Integer> setB = new HashSet<>();
        for (int i : B){
            sumB += i;
            setB.add(i);
        }
        
        int avg = (sumA + sumB) / 2;
        if (sumA > avg){
            int diff = sumA - avg;
            for (int i : A){
                if (setB.contains(i-diff)){
                    res[0] = i ;
                    res[1] = i - diff;
                    return res;
                }
            }
        }else{
            int diff = sumB - avg;
            for (int i : B){
                if (setA.contains(i-diff)){
                    res[0] = i - diff;
                    res[1] = i;
                    return res;
                }
            }
        }
        return res;
    }
}
