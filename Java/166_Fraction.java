
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 49\frac{4}{9}​9​​4​​, the repeating part is obvious. Now try 4333\frac{4}{333}​333​​4​​. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.

Intuition

The key insight here is to notice that once the remainder starts repeating, so does the divided result.

0.1661.000⎯⎯⎯⎯⎯⎯⎯106⎯⎯⎯⎯⎯⎯4036⎯⎯⎯⎯⎯⎯4⇐remainder=1, mark 1 as seen at position=0.⇐remainder=4, mark 4 as seen at position=1.⇐remainder=4 was seen before at position=1, so the fractional part which is 16 starts repeating at position=1 ⇒ 1(6).


Algorithm

You will need a hash table that maps from the remainder to its position of the fractional part. Once you found a repeating remainder, 
you may enclose the reoccurring fractional part with parentheses by consulting the position from the table.

The remainder could be zero while doing the division. That means there is no repeating fractional part and you should stop right away.

Just like the question Divide Two Integers, be wary of edge cases such as negative fractions and nasty extreme case such as
−2147483648−1\frac{-2147483648}{-1}​−1​​−2147483648​​.

Here are some good test cases:
Test case 	Explanation
01\frac{0}{1}​1​​0​​ 	Numerator is zero.
10\frac{1}{0}​0​​1​​ 	Divisor is 0, should handle it by throwing an exception but here we ignore for simplicity sake.
204\frac{20}{4}​4​​20​​ 	Answer is a whole integer, should not contain the fractional part.
12\frac{1}{2}​2​​1​​ 	Answer is 0.5, no recurring decimal.
−14\frac{-1}{4}​4​​−1​​ or 1−4\frac{1}{-4}​−4​​1​​ 	One of the numerator or denominator is negative, fraction is negative.
−1−4\frac{-1}{-4}​−4​​−1​​ 	Both numerator and denominator are negative, should result in positive fraction.
−2147483648−1\frac{-2147483648}{-1}​−1​​−2147483648​​ 	Beware of overflow if you cast to positive.


class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0){
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // if either one is negative not both;
        if ((numerator < 0) ^ (denominator < 0)){
            fraction.append("-");
        }
        // convert to long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend/divisor));
        long remainder = dividend % divisor;
        if (remainder == 0){
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0){
            if (map.containsKey(remainder)){
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder/divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }
}
