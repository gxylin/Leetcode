 Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java. 

 
 http://www.jiuzhang.com/solutions/flatten-2d-vector/

http://massivealgorithms.blogspot.com/2015/11/buttercola-airbnb-2d-iterator-with.html


With remove()
public class Solution{
   private List<List<Integer>> matrix;
   private int rowId;
   private int colId;
   private int numRows;
   
   public Solution(List<List<Integer>> matrix){
      this.matrix = matrix;
      rowId = 0;
      colId = 0;
      numRows = matrix.size();
   }
  
  public boolean hasNext(){
     if (matrix == null || matrix.size() == 0){
        return false;
     }
    while (rowId < numRows && (matrix.get(rowId) == null || matrix.get(rowId).isEmpty())){
       rowId++;
    }
    return rowId < numRows;
  }
  
   public void next(){
     int val = matrix.get(rowId).get(colId);
     colId++;
     if (colId == matrix.get(rowId).size()){
        rowId++;
        colId = 0;
     }
     return val;
   }
   
   public void remove(){
      if (colId == 0){ // Case 1: if the element to remove is the last element of the row
         List<Integer> list = matrix.get(rowId - 1);
         list.remove(list.size() - 1);
       //update rowId: this is just a possible option
         if (list.size() == 0){
           matrix.remove(list);
           rowId--;
         }
      }else{ // Case 2: the element to remove is not the last element
         List<Integer> list = matrix.get(rowId);
         list.remove(colId - 1);
        //update colId
         colId--;
      }
   }
}



Best solution:
Method 2:
 public class Vector2D implements Iterator<Integer> {
    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;
    
    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return j.next();
    }

    @Override
    public boolean hasNext() {
        while (i.hasNext() && (j == null || !j.hasNext())){
            j = i.next().iterator();
        }
        if (j == null){
            return false;
        }
        return j.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Vector2D implements Iterator<Integer> {
    Stack<Integer> stack;
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new Stack<Integer>();
        pushListToStack(vec2d);
    }

    @Override
    public Integer next() {
        if (!hasNext()){
         return null;
        }
        return stack.pop();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushListToStack(List<List<Integer>> list){
        Stack<Integer> temp = new Stack<>();
        for (List<Integer> l : list){
            for (Integer n : l){
                temp.push(n);
            }
        }
        while (!temp.isEmpty()){
            stack.push(temp.pop());
        }
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 
 
