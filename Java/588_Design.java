Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

Example:

Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
Output:
[null,[],null,null,["a"],"hello"]
Explanation:
filesystem

Note:

    You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
    You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
    You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.


Similar as Trie Node
note that for split function
"/".split == > [];
"/a".split == > ["", a]
"/a/".split == > ["", a]



class FileSystem {
    class Node{
        boolean isFile;
        String name;
        String content; 
        Map<String, Node> fileAndFolder; // key: current folder name, value: subfolder or files names
        public Node (boolean isFile, String name, String content, Map<String, Node> fileAndFolder){
            this.isFile = isFile;
            this.name = name;
            this.content = content;
            this.fileAndFolder = fileAndFolder;
        }
    }
    Node root;
    public FileSystem() {
        root = new Node(false, "", "", new TreeMap<String, Node>());
    }
    
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        Node node = root;
        if (!path.equals("/")){
            String[] strs = path.split("/");
            for (int i = 1; i < strs.length; i++){
                node = node.fileAndFolder.get(strs[i]);
            }
            if (node.isFile){
                res.add(node.name);
                return res;
            }
        }
        for (String key : node.fileAndFolder.keySet()){
            res.add(key);
        }
        return res;
    }
    
    public void mkdir(String path) {
        Node node = root;
        String[] strs = path.split("/");
        for (int i = 1; i < strs.length; i++){
            Map<String, Node> map = node.fileAndFolder;
            if (!map.containsKey(strs[i])){
                map.put(strs[i], new Node(false, strs[i], "", new TreeMap<String, Node>()));
            }
            node = map.get(strs[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        Node node = root;
        String[] strs = filePath.split("/");
        for (int i = 1; i < strs.length - 1; i++){
            Map<String, Node> map = node.fileAndFolder;
            node = map.get(strs[i]);
        }
        Map<String, Node> currMap = node.fileAndFolder;
        String name = strs[strs.length-1];
        if (!currMap.containsKey(name)){
            currMap.put(name, new Node(true, name, content, null));
        }else{
            String str = currMap.get(name).content + content;
            currMap.put(name, new Node(true, name, str, null));
        }
    }
    
    public String readContentFromFile(String filePath) {
        Node node = root;
        String[] strs = filePath.split("/");
        for (int i = 1; i < strs.length - 1; i++){
            Map<String, Node> map = node.fileAndFolder;
            node = map.get(strs[i]);
        }
        Map<String, Node> currMap = node.fileAndFolder;
        String name = strs[strs.length-1];
        return currMap.get(name).content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
 
 After remove name which is not necessary
 class FileSystem {
    class Node{
        boolean isFile;
        String content; 
        Map<String, Node> fileAndFolder; // key: current folder name, value: subfolder or files names
        public Node (boolean isFile, String content, Map<String, Node> fileAndFolder){
            this.isFile = isFile;
            this.content = content;
            this.fileAndFolder = fileAndFolder;
        }
    }
    Node root;
    public FileSystem() {
        root = new Node(false, "", new TreeMap<String, Node>());
    }
    
    public List<String> ls(String path) {
        List<String> res = new ArrayList<>();
        Node node = root;
        if (!path.equals("/")){
            String[] strs = path.split("/");
            for (int i = 1; i < strs.length; i++){
                node = node.fileAndFolder.get(strs[i]);
            }
            if (node.isFile){
                res.add(strs[strs.length-1]);
                return res;
            }
        }
        for (String key : node.fileAndFolder.keySet()){
            res.add(key);
        }
        return res;
    }
    
    public void mkdir(String path) {
        Node node = root;
        String[] strs = path.split("/");
        for (int i = 1; i < strs.length; i++){
            Map<String, Node> map = node.fileAndFolder;
            if (!map.containsKey(strs[i])){
                map.put(strs[i], new Node(false, "", new TreeMap<String, Node>()));
            }
            node = map.get(strs[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        Node node = root;
        String[] strs = filePath.split("/");
        for (int i = 1; i < strs.length - 1; i++){
            Map<String, Node> map = node.fileAndFolder;
            node = map.get(strs[i]);
        }
        Map<String, Node> currMap = node.fileAndFolder;
        String name = strs[strs.length-1];
        if (!currMap.containsKey(name)){
            currMap.put(name, new Node(true, content, null));
        }else{
            String str = currMap.get(name).content + content;
            currMap.put(name, new Node(true, str, null));
        }
    }
    
    public String readContentFromFile(String filePath) {
        Node node = root;
        String[] strs = filePath.split("/");
        for (int i = 1; i < strs.length - 1; i++){
            Map<String, Node> map = node.fileAndFolder;
            node = map.get(strs[i]);
        }
        Map<String, Node> currMap = node.fileAndFolder;
        String name = strs[strs.length-1];
        return currMap.get(name).content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
 
