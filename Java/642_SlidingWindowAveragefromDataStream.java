Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Have you met this question in a real interview? Yes
Example
MovingAverage m = new MovingAverage(3);
m.next(1) = 1 // return 1.00000
m.next(10) = (1 + 10) / 2 // return 5.50000
m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
m.next(5) = (10 + 3 + 5) / 3 // return 6.00000

Method:
• 方便快速求一段的和
– a[k] + a[k + 1] +... + a[j] = s[j] - s[k -1] 时间复杂度o(1)
• 怎样快求s[i] ?
– s[i] = s[i - 1] + a[i] 时间复杂度o(1)
如何节省储存空间呢?(2种方法) 
1. 链表保存sum
2. 数组滚动
• sum[4]-sum[1]时,sum[0]这个位置空出来了,sum[4]可以放到sum[0] • 这样算实际位置
– 逻辑位置取mod,这一题mod (size+1)
 小技巧总结:
• 如何快速求和? 前缀和数组(dummy 0)
• 如何节省储存空间呢? 滚动
• 写滚动的技巧 先写程序最后加滚动

public class MovingAverage {
    private int size;
    private double[] sum;
    private int id;
    /*
    * @param size: An integer
    */public MovingAverage(int size) {
        this.size = size;
        id = 0;
        sum = new double[size + 1];
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        id++;
        sum[mod(id)] = sum[mod(id-1)] + val;
        if (id >= size){
            return (sum[mod(id)] - sum[mod(id - size)]) / size;
        }else{
            return sum[mod(id)] / id;
        }
    }
    private int mod(int k){
        return k % (size + 1);
    }
}
