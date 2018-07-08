Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in 
adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return 
if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False

Method 1:
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0){
            return true;
        }
        int count = 0;
        int[] buff = new int[flowerbed.length+2];
        for (int i = 1; i < buff.length - 1; i++){
            buff[i] = flowerbed[i-1];
        }
        for (int i = 0; i < buff.length; i++){
            if(buff[i] == 0){
                count++;
                if (count == 3){
                    n--;
                    if (n == 0){
                        return true;
                    }
                    count = 1;
                }
            }else{
                count = 0;
            }
        }
        return false;
    }
}

Method 2: Best solution
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length - 1 || flowerbed[i+1] == 0)){
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }
}

