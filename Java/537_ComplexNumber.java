Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.


class Solution {
    public String complexNumberMultiply(String a, String b) {
        int[] aNum = parse(a);
        int[] bNum = parse(b);
        String real = String.valueOf(aNum[0] * bNum[0] - aNum[1] * bNum[1]);
        String imag = String.valueOf(aNum[0] * bNum[1] + aNum[1] * bNum[0]);
        return  real + "+" + imag +"i";
    }
    private int[] parse(String a){
        int[] result = new int[2];
        int index = 0;
        for (int k = 0; k < a.length(); k++){
            if (a.charAt(k) == '+'){
                result[0] = Integer.parseInt(a.substring(index, k));
                index = k + 1;
            }
            if (a.charAt(k) == 'i'){
                result[1] = Integer.parseInt(a.substring(index, k));
            }
        }
        return result;
    }
}


class Solution {
    public String complexNumberMultiply(String a, String b) {
        int[] A = parse(a);
        int[] B = parse(b);
        StringBuilder sb = new StringBuilder();
        int real = A[0] * B[0] - A[1] * B[1];
        int imag = A[0] * B[1] + A[1] * B[0];
        sb.append(real + "+" + imag + "i");
        return sb.toString();
    }
    private int[] parse(String S){
        int[] res = new int[2];
        int i = 0;
        int index = S.indexOf("+");
        res[0] = Integer.parseInt(S.substring(0, index));
        res[1] = Integer.parseInt(S.substring(index+1, S.length() - 1));
        return res;
    }
}
