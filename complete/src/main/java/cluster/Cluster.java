package cluster;

public class Cluster {
    Cluster left = null;
    Cluster right = null;
    Cluster parent = null;
    Blog blog = null;
    private double distance = 0;

    Cluster(Blog b) {
        this.blog = b;
    }

    private Cluster() {}


    public Cluster merge(Cluster oc, double distance) {
        Cluster newCluster = new Cluster();

        newCluster.left = this;
        this.parent = newCluster;
        newCluster.right = oc;
        oc.parent = newCluster;

        Blog newBlog = new Blog("");
        for (int i = 0; i < blog.blogWords.size(); i++) {
            Word currentClusterWord = blog.blogWords.get(i);
            Word oldClusterWord = oc.blog.blogWords.get(i);
            double count = (currentClusterWord.count + oldClusterWord.count) / 2.0;
            newBlog.blogWords.add(new Word(currentClusterWord.word, count));
        }

        newCluster.blog = newBlog;
        newCluster.distance = distance;
        return newCluster;
    }


    public String toString() {
        return this.blog.blogName;
    }
}
