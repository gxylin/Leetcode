Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Example:

MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);  
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false


class MyQueue {
    Stack<Integer> masterStack;
    Stack<Integer> slaveStack;
    /** Initialize your data structure here. */
    public MyQueue() {
        masterStack = new Stack<>();
        slaveStack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        slaveStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!masterStack.isEmpty()){
            return masterStack.pop();
        }
        while (!slaveStack.isEmpty()){
            masterStack.push(slaveStack.pop());
        }
        return masterStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (!masterStack.isEmpty()){
            return masterStack.peek();
        }
        while (!slaveStack.isEmpty()){
            masterStack.push(slaveStack.pop());
        }
        return masterStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return masterStack.isEmpty() && slaveStack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
