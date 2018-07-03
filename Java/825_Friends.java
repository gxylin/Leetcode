Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.


Method 1: Brute Force: TLE
Time complexity:O(N^2)
Space complexity: O(1)
class Solution {
    public int numFriendRequests(int[] ages) {
        int count = 0;
        for (int i = 0; i < ages.length; i++){
            for (int j = 0; j < ages.length; j++){
                if (i != j && ages[j] > 0.5 *ages[i] + 7 && ages[j] <= ages[i] && (ages[j] <= 100 || ages[i] >= 100)){
                    count++;
                }
            }
        }
        return count;
    }
}

Method 2: counting
Time complexity: O(A^2 + N), where N is the number of people, and A is the number of ages.
Space complexity: O(A)
class Solution {
    public int numFriendRequests(int[] ages) {
        int ans = 0;
        int[] counts = new int[121];
        for (int age : ages){
            counts[age]++;
        }
        for (int i = 1; i < counts.length; i++){
            for (int j = 1; j < counts.length; j++){
                if (j <= 0.5 * i + 7 || j > i || (j > 100 && i < 100)){
                    continue;
                }
                if (i != j){
                    ans += counts[i] * counts[j];
                }else{
                    ans += counts[i] * (counts[i] - 1);
                }
            }
        }
        return ans;
    }
}

Intuition

Instead of processing all 20000 people, we can process pairs of (age, count) representing how many people are that age. 
Since there are only 120 possible ages, this is a much faster loop.

Algorithm

For each pair (ageA, countA), (ageB, countB), if the conditions are satisfied with respect to age, then countA * countB 
pairs of people made friend requests.

If ageA == ageB, then we overcounted: we should have countA * (countA - 1) pairs of people making friend requests instead, as you cannot friend request yourself.
