The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1:

Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".

Example 2:

Input: buf = "abcde", n = 5 
Output: "abcde"

Note:
The read function will only be called once for each test case.

Note that 
思路：文件中的字符数可能小于n，所以不断借助read4读取字符的时候需要判断是否已经读到文件末尾。每次将read4中字符复制到buf中时，
也需要判断好能够复制的上限。
注意， n也可能小于文件的字数

The meaning here is that read4() function will read 4 characters at a time from a file and then put the characters that has been read
into this buf variable.
So read() function is reading at most n characters from a file ( we don’t know what file and how it’s reading from the file), and 
put x characters into char[] buf.
  
  
https://www.jianshu.com/p/b06b90a52617?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation

buf: the destination buffer
n: max number of char to read
return the number of char that was read

public int read(char[] buf, int n) {
  boolean eof = false;      // end of file flag
  int total = 0;            // total bytes have read
  char[] tmp = new char[4]; // temp buffer
  
  while (!eof && total < n) {
    int count = read4(tmp);
    
    // check if it's the end of the file
    eof = count < 4;
    
    // get the actual needed count to put into buf
    count = Math.min(count, n - total);
    
    // copy from temp buffer to buf
    for (int i = 0; i < count; i++) 
      buf[total++] = tmp[i];
  }
  
  return total;
}

