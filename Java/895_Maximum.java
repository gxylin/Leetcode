Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 

Example 1:

Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].
 

Note:

Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

Method 1: TLE
Push: O(1)
Pop: O(n)
class FreqStack {
    Map<Integer, Integer> map;
    Stack<Integer> stack;
    public FreqStack() {
        map = new HashMap<>();
        stack = new Stack<>();
    }
    
    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        stack.push(x);
    }
    
    public int pop() {
        int res = 0;
        Stack<Integer> temp = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int maxFreq = 0;
        for (int i : map.keySet()){
            maxFreq = Math.max(maxFreq, map.get(i));
        }
        for (int i : map.keySet()){
            if (maxFreq == map.get(i)){
                set.add(i);
            }
        }
        while (!stack.isEmpty()){
            if (set.contains(stack.peek())){
                res = stack.pop();
                map.put(res, map.get(res) - 1);
                while (!temp.isEmpty()){
                    stack.push(temp.pop());
                }
                break;
            }else{
                temp.push(stack.pop());
            }
        }
        return res;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
 
 
 Method 2: Best solution
 Push: O(1)
 Pop: O(1)
 class FreqStack {
    Map<Integer, Integer> freqMap; // key: num; value: frequency
    Map<Integer, Stack<Integer>> stackMap; // key: frequency; value: stack of num 
    int maxFreq;
    public FreqStack() {
        freqMap = new HashMap<>();
        stackMap = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int x) {
        int freq = freqMap.getOrDefault(x, 0) + 1;
        maxFreq = Math.max(maxFreq, freq);
        freqMap.put(x, freq);
        if (!stackMap.containsKey(freq)){
            stackMap.put(freq, new Stack<Integer>());
        }
        stackMap.get(freq).push(x);
    }
    
    public int pop() {
        int val = stackMap.get(maxFreq).pop();
        freqMap.put(val, freqMap.get(val) - 1);
        if (stackMap.get(maxFreq).isEmpty()){
            maxFreq--;
        }
        return val;
    }
}
