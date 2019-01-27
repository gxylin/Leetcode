Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

Now the question is, how do we know that we've reached the half of the number?

Since we divided the number by 10, and multiplied the reversed number by 10, when the original 
number is less than the reversed number, it means we've processed half of the number digits.

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0 )){
            return false;
        }
        int reverse = 0;
        while (x > reverse){
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }
}

Better version:
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0){
            return false;
        }
        int reverse = 0;
        int orig = x;
        while (x > 0){
            reverse = reverse * 10 + x%10;
            x /= 10;
        }
        return reverse == orig;
    }
}
