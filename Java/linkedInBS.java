Binary search 的应用，找比自己大的字母，arr = [a, b, c, d, f, v, z] key = z, ret = a; arr = [a, b, d,
f, v, z], key = b, ret = d

     35. Search Insert Position

     Must see https://github.com/optimisea/Leetcode/blob/master/Java/35_Search.java
 
 Method 1: 
Note that for this template, in most cases target should be between low (inclusive) and high (inclusive) 
     But there are two corner cases
     case 1: target less than low, e.g. [1,2,3] target = 0 == > low will be 1, high will be 2
     case 2: target greater than high, e.g. [1,2,3] target = 4 == > low will be 2, high will be 3
    
class MyCode {
  public static int bs (char[] arr, char key){
    int n = arr.length;
    int start = 0;
    int end = n - 1;
    while (start + 1 < end){
      int mid = start + (end - start) / 2;
      if (arr[mid] == key){
        if (mid == n-1){
          return arr[0];
        }else{
          return arr[mid+1];
        }
      }else if (arr[mid] > key){
        end = mid;
      }else{
        start = mid;
      }
    }
    if (arr[start] > key){ 
      return arr[start];
    }else if (arr[end] > key){
      return arr[end];
    }
    return arr[0];
  }
	public static void main (String[] args) {
		char[] arr = {'b', 'd', 's'};
    System.out.println((char)bs(arr, 'a'));
	}
}


Method 2: 


// package whatever; // don't place package name!

import java.io.*;

class MyCode {
  public static int bs (char[] arr, char key){
    int n = arr.length;
    int start = 0;
    int end = n - 1;
    while (start <= end){
      int mid = start + (end - start) / 2;
      if (arr[mid] == key){
        if (mid == n-1){
          return arr[0];
        }else{
          return arr[mid+1];
        }
      }else if (arr[mid] > key){
        end = mid-1;
      }else{
        start = mid+1;
      }
    }
    //start will be the position to be inserted to keep sorted (check leetcode 35)
    if (start == n){
      return arr[0];
    }
    if (arr[start] > key){
      return arr[start];
    }else if (start < n-1){//implicitily arr[start] == key
      return arr[start+1];
    }
    return arr[0];
  }
	public static void main (String[] args) {
		char[] arr = {'b', 'd', 's'};
    System.out.println((char)bs(arr, 'b'));
	}
}
