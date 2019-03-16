Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and 
takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that 
represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true
 

Note:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='

Method 1: Union Find
class Solution {
   class UF{
        int[] parent;
        int[] size;
        int count;
        public UF(int N){
            parent = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (parent[x] == x){
                return x;
            }
            return parent[x] = find(parent[x]);// path compression
            //return find(parent[x]); //not path compression
        }
        public void union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b){
                parent[root_a] = root_b;
                size[root_b] += size[root_a];
                count--;
            }
        }
        public boolean connect(int a, int b){
            return find(a) == find(b);
        }
        public int size(){
            return count;
        }
    }
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations){
            int first = (int)(equation.charAt(0) - 'a');
            int second = (int)(equation.charAt(3) - 'a');
            char equal = equation.charAt(1);
            if (equal == '='){
                uf.union(first, second);
            }
        }
        for (String equation : equations){
            int first = (int)(equation.charAt(0) - 'a');
            int second = (int)(equation.charAt(3) - 'a');
            char equal = equation.charAt(1);
            if (equal == '!'){
                if (uf.connect(first, second)){
                    return false;
                }
            }
        }
        return true;
    }
}

