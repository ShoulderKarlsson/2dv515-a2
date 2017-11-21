package cluster;

class Word {
    String word;
    double count;

    Word(String word, double count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public double getCount() {
        return count;
    }
}