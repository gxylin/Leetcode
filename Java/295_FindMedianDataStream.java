Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

class MedianFinder {
    private Queue<Integer> minHeap; // keep the larger half
    private Queue<Integer> maxHeap; // keep the smaller half
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b.compareTo(a);
            }
        });
    }
    
    public void addNum(int num) {
        if (minHeap.size() == 0 && maxHeap.size() == 0){
            minHeap.add(num);
        }else if (minHeap.size() > maxHeap.size()){
            if (num > minHeap.peek()){
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }else{
                maxHeap.offer(num);
            }
        }else if (minHeap.size() < maxHeap.size()){
            if (num < maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }
        }else{
            if (num > minHeap.peek()){
                minHeap.offer(num);
            }else{
                maxHeap.offer(num);
            }
        }
    }
    
    public double findMedian() {
        if (minHeap.size() == 0 && maxHeap.size() == 0){
            return 0.0;
        }
        if (minHeap.size() > maxHeap.size()){
            return (double) minHeap.peek();
        }
        if (maxHeap.size() > minHeap.size()){
            return (double) maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
 
 Better version:
class MedianFinder {
    Queue<Integer> maxHeap;
    Queue<Integer> minHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare (Integer i1, Integer i2){
                return (int)(i2 - i1);
            }
        });
    }
    
    public void addNum(int num) {
        if (minHeap.isEmpty()){
            minHeap.offer(num);
        }else{
            if (num < minHeap.peek()){
                maxHeap.offer(num);
                if (maxHeap.size() > minHeap.size()){
                    minHeap.offer(maxHeap.poll());
                }
            }else{
                minHeap.offer(num);
                if (minHeap.size() > maxHeap.size() + 1){
                    maxHeap.offer(minHeap.poll());
                }
            }
        }
    }
    
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()){
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return (double)minHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
