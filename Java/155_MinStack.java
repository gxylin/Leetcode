Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

class MinStack {
    
    Stack<Integer> stack;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (top() == min){
            stack.pop();
            min = stack.pop();
        }else{
            stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


Method 2:
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        stack.push(number);
        if (minStack.empty()){
            minStack.push(number);
        }else{
            minStack.push(Math.min(minStack.peek(), number));
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        minStack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        return minStack.peek();
    }
}
