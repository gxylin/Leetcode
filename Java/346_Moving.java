Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

Method 1: 
Time complexity: O(n)
Space complexity: O(n)
class MovingAverage {
    Queue<Integer> queue;
    int size;
    int count = 0;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
        count++;
        queue.offer(val);
        double sum = 0;
        if (count <= size){
            for (int i = 0; i < count; i++){
                int temp = queue.poll();
                sum += temp;
                queue.offer(temp);
            }
        }else{
            queue.poll();
            for (int i = 0; i < size; i++){
                int temp = queue.poll();
                sum += temp;
                queue.offer(temp);
            }
        }
        return sum / Math.min(count, size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
 
 Method 2: Best solution
 Time complexity: O(1)
 Space complexity: O(n)
 class MovingAverage {
    Queue<Integer> queue;
    int size;
    int count = 0;
    double sum = 0;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
        count++;
        queue.offer(val);
        sum += val; 
        if (count > size){
            sum -= queue.poll();
            count--;
        }
        return sum / count;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
 
