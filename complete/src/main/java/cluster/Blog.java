package cluster;

import java.util.ArrayList;

public class Blog {
    public String blogName;
    public ArrayList<Word> blogWords = new ArrayList<>();

    Blog(String blogName) {
        this.blogName = blogName;
    }

    private void addWord(String word, int count) {
        this.blogWords.add(new Word(word, count));
    }
}
