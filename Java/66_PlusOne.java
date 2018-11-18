Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            if (digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length+1];
        result[0] = 1;
        return result;
    }
}

class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--){
            int num = digits[i] + carry;
            digits[i] = num % 10;
            carry = num / 10;
        }
        if (carry == 1){
            int[] res = new int[digits.length+1];
            res[0] = 1;
            for (int i = 1; i < res.length; i++){
                res[i] = digits[i-1];
            }
            return res;
        }
        return digits;
    }
}
