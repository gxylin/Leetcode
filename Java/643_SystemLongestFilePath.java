Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string

"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

 Notice

The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.
Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
Have you met this question in a real interview? Yes
Example
Give input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" return

思路:
• 有点小麻烦的纯模拟
• 技巧一:用split(‘\n’) 将原串分割开,相当于一次读一行
• 技巧二:利用’\t’的个数来当前目录/文件 在第几层
• 技巧三:从上到下一行一行读入的顺序像dfs的顺序,所以可以把前面 几层的字符串长度都记下来(画图模拟)

Note that consider "\t" as a whole, so the following output is 2.
import java.io.*;

class MyCode {
  public static void main (String[] args) {
      String Str = new String("\t\t\t");
      System.out.print("Found Last Index :" );
      System.out.println(Str.lastIndexOf("\t"));  }
}



public class Solution {
    /*
     * @param input: an abstract file system
     * @return: return the length of the longest absolute path to file
     */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0){
            return 0;
        }
        int ans = 0;
        int[] level_size = new int[input.length()+1];
        for (String line : input.split("\n")){
            int level = line.lastIndexOf("\t") + 2;  //can't find, return -1; if found, "\t" together as one index
            int len = line.length() - (level - 1);
            if (line.contains(".")){
                ans = Math.max(ans, level_size[level - 1] + len);
            }else{
                level_size[level] = level_size[level - 1] + len + 1;
            }
        }
        return ans;
    }
}
