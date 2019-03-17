https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step
Some applications of monotone (increase/decrease) stack in leetcode:
Next Greater Element II (a very basic one)
Largest Rectangle in Histogram(almost the same as this problem)
Maximal Rectangle(please do this problem after you solve the above one)
Trapping Rain Water (challenge)
Remove Duplicate Letters(challenge)
Remove K Digits
Create Maximum Number 132 Pattern(challenge, instead of focusing on the elements in the stack, this problem focuses on 
the elements poped from the monotone stack)
    
sliding window maximum(challenge, monotone queue)
Max Chunks To Make Sorted II


Check Leetcode 907 Sum of Subarray Minimums
Better version:
https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C%2B%2BJavaPython-Stack-Solution
class Solution {
    public int sumSubarrayMins(int[] A) {
        int res = 0, n = A.length, mod = (int)1e9 + 7;
        int[] left = new int[n], right = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] {A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i]) // use == to handle duplicate elements, only count one time either in left or right
                count += s2.pop()[1];
            s2.push(new int[] {A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + A[i] * left[i] * right[i]) % mod;
        return res;
    }
}

Best of Best:
Similar as Leetcode 901 Online Stock Span
class Solution {
    public int sumSubarrayMins(int[] A) {
        int res = 0, n = A.length, mod = (int)1e9 + 7;
        int[] left = new int[n], right = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] {A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= A[i]) // use == to handle duplicate elements, only count one time either in left or right
                count += s2.pop()[1];
            s2.push(new int[] {A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + A[i] * left[i] * right[i]) % mod;
        return res;
    }
}
