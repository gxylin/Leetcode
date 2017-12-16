The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

 Notice

The read function may be called multiple times.


/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

思路:
• 做一个buffer,类似内存从磁盘读数据
• buffer是一个队列:
– 队列先进先出可以保持顺序不变
– 队列为空时就进队(read4)
– 队列不为空时就出队,并把出队的元素放到答案中

public class Solution extends Reader4 {
    /**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    char[] buffer = new char[4];
    int bufferPrt = 0;
    int bufferCnt = 0;
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n){
            if (bufferPrt == bufferCnt){
                bufferCnt = read4(buffer);
                bufferPrt = 0;
            }
            if (bufferCnt == 0){
                break;
            }
            while (ptr < n && bufferPrt < bufferCnt){
                buf[ptr++] = buffer[bufferPrt++];
            }
        }
        return ptr;
    }
}
