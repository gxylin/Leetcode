The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function may be called multiple times.


https://www.youtube.com/watch?v=5gO5syMOKnI

https://www.lintcode.com/problem/read-n-characters-given-read4-ii-call-multiple-times/description

Public class Solution Extends Read4{
  @buffer: destination buffer
  @n: max character that can read
  @return the number of character can be read
  
  private char[] tmp = new char[4];
  private tmpCnt = 0; //pointer in tmp
  private tmpPtr = 0; //how many char is read in tmp
  
  Format 1:
  public int read(char[] buff, int n){
      int total = 0;
      while (total < n){
         if (tmpPtr == 0){//either not start or already read all tmp character
           tmpCnt = read4(tmp);
         }
         if (tmpCnt == 0){//end of file
            break;
         }
         while (total < n && tmpPtr < tmpCnt){
            buff[total++] = tmp[tmpPtr++];
         }
         if (tmpPtr == tmpCnt){
             tmpPtr = 0;
         }
         if (tmpCnt < 4){
            break;
         }
      }
      return total;
  }
}


Format 2:

public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
     
    private char[] tmp = new char[4];
    private int tmpCnt = 0;
    private int tmpPtr = 0;
    public int read(char[] buf, int n) {
        int total = 0;
        while (total < n){
            if (tmpPtr == 0){
                tmpCnt = read4(tmp);
            }
            while (total < n && tmpPtr < tmpCnt){
                buf[total++] = tmp[tmpPtr++];
            }
            if (tmpPtr == tmpCnt){
                tmpPtr = 0;
            }
            if (tmpCnt < 4){
                break;
            }
        }
        return total;
    }
}



Format 3:
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
     
    private char[] tmp = new char[4];
    private int tmpCnt = 0;
    private int tmpPtr = 0;
    public int read(char[] buf, int n) {
        int total = 0;
        while (total < n){
            if (tmpPtr == 0){
                tmpCnt = read4(tmp);
            }
            if (tmpCnt == 0){
                break;
            }
            while (total < n && tmpPtr < tmpCnt){
                buf[total++] = tmp[tmpPtr++];
            }
            if (tmpPtr == tmpCnt){
                tmpPtr = 0;
            }
        }
        return total;
    }
}
