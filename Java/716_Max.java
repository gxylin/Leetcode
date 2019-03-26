Design a max stack that supports push, pop, top, peekMax and popMax.

    push(x) -- Push element x onto stack.
    pop() -- Remove the element on top of the stack and return it.
    top() -- Get the element on the top.
    peekMax() -- Retrieve the maximum element in the stack.
    popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

Example 1:

MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5

Note:

    -1e7 <= x <= 1e7
    Number of operations won't exceed 10000.
    The last four operations won't be called when stack is empty.


Similar to 155. Min Stack Method 2

Method 1: Two stacks O(N)
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()){
            maxStack.push(x);
        }else{
            maxStack.push(Math.max(maxStack.peek(), x));
        }
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> buffer = new Stack<>();
        while (top() != max){
            buffer.push(pop()); // must be pop(), not stack.pop() or maxStack.pop()
        }
        stack.pop();
        maxStack.pop();
        while (!buffer.isEmpty()){
            push(buffer.pop());
        }
        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

Method 2: Double Linked List + TreeMap
O(LogN)
   https://leetcode.com/articles/max-stack/
