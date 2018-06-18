Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].

Intuition

One initial thought that comes to mind is we can always have a candidate answer of d999...9 (a digit 0 <= d <= 9 followed by some number of nines.) For example if N = 432543654, we could always have an answer of at least 399999999.

We can do better. For example, when the number is 123454321, we could have a candidate of 123449999. It seems like a decent strategy is to take a monotone increasing prefix of N, then decrease the number before the "cliff" (the index where adjacent digits decrease for the first time) if it exists, and replace the rest of the characters with 9s.

When does that strategy fail? If N = 333222, then our strategy would give us the candidate answer of 332999 - but this isn't monotone increasing. However, since we are looking at all indexes before the original first occurrence of a cliff, the only place where a cliff could exist, is next to where we just decremented a digit.

Thus, we can repair our strategy, by successfully morphing our answer 332999 -> 329999 -> 299999 with a linear scan.

Algorithm

We'll find the first cliff S[i-1] > S[i]. Then, while the cliff exists, we'll decrement the appropriate digit and move i back. Finally, we'll make the rest of the digits 9s and return our work.

We can prove our algorithm is correct because every time we encounter a cliff, the digit we decrement has to decrease by at least 1. Then, the largest possible selection for the rest of the digits is all nines, which is always going to be monotone increasing with respect to the other digits occurring earlier in the number.


example: 
123454321   --> 123449999
332999 -> 329999 -> 299999

class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] S = String.valueOf(N).toCharArray();
        int i = 1;
        while (i < S.length && S[i] >= S[i-1]){
            i++;
        }
        while (i > 0 && i < S.length && S[i] < S[i-1]){
            i--;
            S[i]--;
        }
        for (int j = i + 1; j < S.length; j++){
            S[j] = '9';
        }
        return Integer.parseInt(String.valueOf(S));
    }
}
