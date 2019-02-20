In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1

Method 1: Group by Zero
class Solution {
    public int maxDistToClosest(int[] seats) {
        int max = 1;
        int i = 0;
        int start = 0;
        while (i < seats.length){
            if (seats[i] == 1){
                max = Math.max(max, (i - start) / 2 );
                start = i;
            }
            i++;
        }
        //deal with corner case
        int count = 0;
        for (int j = 0; j < seats.length; j++){
            if (seats[j] == 1){
                max = Math.max(max, count);
                break;
            }
            count++;
        }
        count = 0;
        for (int j = seats.length - 1; j >= 0; j--){
            if (seats[j] == 1){
                max = Math.max(max, count);
                break;
            }
            count++;
        }
        return max;
    }
}

Group by Zero
class Solution {
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int K = 0; //current longest group of empty seats
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                K = 0;
            } else {
                K++;
                ans = Math.max(ans, (K + 1) / 2);
            }
        }

        for (int i = 0; i < N; ++i)  if (seats[i] == 1) {
            ans = Math.max(ans, i);
            break;
        }

        for (int i = N-1; i >= 0; --i)  if (seats[i] == 1) {
            ans = Math.max(ans, N - 1 - i);
            break;
        }

        return ans;
    }
}

Method 2: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int maxDistToClosest(int[] seats) {
        int count = 0;
        int res = 0;
        int firstInd =  0;
        int lastInd = seats.length - 1;
        for (int i = 0; i < seats.length; i++){
            if (seats[i] == 0){
                count++;
            }else{
                res = count;
                firstInd = i;
                break;
            }
        }
        count = 0;
        for (int i = seats.length - 1; i >= 0; i--){
            if (seats[i] == 0){
                count++;
            }else{
                res = Math.max(res, count);
                lastInd = i;
                break;
            }
        }
        if (firstInd == lastInd){
            return res;
        }
        count = 0;
        for (int i = firstInd + 1; i <= lastInd; i++){
            if (seats[i] == 0){
                count++;
            }else{
                if (count % 2 == 0){
                    res = Math.max(res, count/2);
                }else{
                    res = Math.max(res, count/2+1);
                }
                count = 0;
            }
        }
        return res;
    }
}

Method 3: Similar as  https://github.com/optimisea/Leetcode/blob/master/Java/821_Shortest.java
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int pos = -2 * n;
        int[] res = new int[n];
        for (int i = 0; i < seats.length; i++){
            if (seats[i] == 1){
                pos = i;
            }
            res[i] = i - pos;
        }
        pos = 2 * n;
        for (int i = n - 1; i >= 0; i--){
            if (seats[i] == 1){
                pos = i;
            }
            res[i] = Math.min(res[i], pos - i);
        }
        int max = 0;
        for (int i = 0; i < n; i++){
            max = Math.max(max, res[i]);
        }
        return max;
    }
}
