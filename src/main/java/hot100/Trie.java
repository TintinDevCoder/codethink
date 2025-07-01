package hot100;

class Trie {
    boolean isEnd;
    Trie[] children;
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    
    public void insert(String word) {
        char[] charArray = word.toCharArray();
        Trie n = this;
        for (char c : charArray) {
            int b = c - 'a';
            Trie[] tries = n.children;
            if (tries[b] == null) tries[b] = new Trie();
            n = tries[b];
        }
        n.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie trie = preSearch(word);
        return trie != null && trie.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return preSearch(prefix) != null;
    }
    public Trie preSearch(String word) {
        char[] charArray = word.toCharArray();
        Trie n = this;
        for (char c : charArray) {
            int b = c - 'a';
            Trie[] tries = n.children;
            if (tries[b] == null) return null;
            n = tries[b];
        }
        return n;
    }
}