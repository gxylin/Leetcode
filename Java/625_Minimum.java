Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48 

Output:

68

Example 2
Input:

15

Output:

35

Factorization:
We know that the final number generated, resresres, should be such that its digits should have a product equal to the given number aaa.
In other words, the digits of resresres will be the factors of the given number aaa. Thus, our problem reduces to finding the factors
(not necessarily prime) of aaa and finding their smallest possible arrangement. Thus, we start with trying with the largest possible 
factor 999, obtain as many such counts of this factor as possible in resresres and place such factors obtianed at its least significant 
positions. Then, we go on decrementing the number currently considered as the possible factor and if it is a factor, we keep on placing 
it at relatively more significant positions in resresres. We go on getting such factors till we are done considering all the numbers
from 9 to 2. At the end, resresres gives the required result.

class Solution {
    public int smallestFactorization(int a) {
        if (a < 2){
            return a;
        }
        long res = 0;
        long multi = 1;
        for (int i = 9; i >= 2; i--){
            while (a % i == 0){
                a = a / i;
                res += i * multi;
                multi = multi * 10;
            }
        }
        return a < 10 && res <= Integer.MAX_VALUE ? (int)res : 0;
    }
}

