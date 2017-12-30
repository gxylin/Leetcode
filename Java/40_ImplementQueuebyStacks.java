As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Have you met this question in a real interview? Yes
Example
push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2

public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (stack2.isEmpty()){
            moveStack1To2();
        }
        return stack2.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (stack2.empty()){
            moveStack1To2();
        }
        return stack2.peek();
    }
    private void moveStack1To2(){
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
    }
}
