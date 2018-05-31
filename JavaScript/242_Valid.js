Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?


http://exploringjs.com/es6/ch_maps-sets.html#sec_overview-maps-sets


/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    const map = new Map();
    for (let i = 0; i < s.length; i++){
        let ch = s.charAt(i);
        if (map.has(ch)){
            map.set(ch, map.get(ch) + 1);
        }else{
            map.set(ch, 1);
        }
    }
    for (let i = 0; i < t.length; i++){
        let ch = t.charAt(i);
        if (!map.has(ch)){
            return false;
        }else{
            map.set(ch, map.get(ch) - 1);
        }
    }
    for (let key of map.keys()){
        if (map.get(key) !== 0){
            return false;
        }
    }
    return true;
};
