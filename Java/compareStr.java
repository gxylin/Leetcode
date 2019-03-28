https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=504403&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3109%5D%5Bvalue%5D%3D2%26searchoption%5B3109%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline

题目：
写一个compare 方法， 比较两个字符串的大小。字符串可以包含任何的ASCII字符。要求，把字符串中的数字当成一个整体比较， 比如：

s1： abc0123ac
s2:   abc5ac
返回 1， s1 > s2. 因为 0123 > 5.

s1:  abc555
s2:  abcd
返回 1，s1 > s2. 这时候需要比较555和d的ASCII number的大小。. check 1point3acres for more.

s1:  abc0123
s2:  abc123
返回 0， s1 = s2. 因为0123 = 123

s1:  abc097
s2:  abca
返回 0， s1 = s2. 因为 a的ASCII number = 97

s1: abc
s2: abcd
返回 -1， s1 < s2. 字典顺序

int compare(String s1, String s2) ;


import java.io.*;

class MyCode {
  public static int compareStr(String S1, String S2){
    int i = 0;
    int j = 0;
    while (i < S1.length() && j < S2.length()){
      char c1 = S1.charAt(i);
      char c2 = S2.charAt(j);
      if (Character.isLetter(c1) && Character.isLetter(c2)){
        if (c1 < c2){
          return -1;
        }else if (c1 > c2){
          return 1;
        }
        i++;
        j++;
      }else if (Character.isDigit(c1) && Character.isLetter(c2)){
        int num1 = 0;
        while (i < S1.length() && Character.isDigit(S1.charAt(i))){
          num1 = num1 * 10 + (int)(S1.charAt(i) - '0');
          i++;
        }
        int num2 = (int)(c2);
        if (num1 < num2){
          return -1;
        }else if (num1 > num2){
          return 1;
        }
        j++;
      }else if (Character.isLetter(c1) && Character.isDigit(c2)){
        int num2 = 0;
        while (j < S2.length() && Character.isDigit(S2.charAt(j))){
          num2 = num2 * 10 + (int)(S2.charAt(j) - '0');
          j++;
        }
        int num1 = (int)(c1);
        if (num1 < num2){
          return -1;
        }else if (num1 > num2){
          return 1;
        }
        i++;
      }else{
        int num1 = 0;
        while (i < S1.length() && Character.isDigit(S1.charAt(i))){
          num1 = num1 * 10 + (int)(S1.charAt(i) - '0');
          i++;
        }
        int num2 = 0;
        while (j < S2.length() && Character.isDigit(S2.charAt(j))){
          num2 = num2 * 10 + (int)(S2.charAt(j) - '0');
          j++;
        }
        if (num1 < num2){
          return -1;
        }else if (num1 > num2){
          return 1;
        }
      }
    }
    if (i == S1.length() && j == S2.length()){
      return 0;
    }else if (i == S1.length()){
      return -1;
    }
    return 1;
  }
	public static void main (String[] args) {
		String str1 = "abc0123";
    String str2 = "abc123";
    System.out.println(compareStr(str1, str2));
	}
}
