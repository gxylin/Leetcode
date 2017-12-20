We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

Have you met this question in a real interview? Yes
Example
n = 10, I pick 4 (but you don't know)

Return 4. Correct !

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    /**
     * @param n an integer
     * @return the number you guess
     */
    public int guessNumber(int n) {
        int l = 1;
        int r = n;
        while (l + 1 < r){
            int mid = l + (r - l) / 2;
            int res = guess(mid);
            if (res == 0){
                return mid;
            }else if (res == -1){
                r = mid;
            }else{
                l = mid;
            }
        }
        if (guess(l) == 0){
            return l;
        }
        if (guess(r) == 0){
            return r;
        }
        return 0;
    }
}
