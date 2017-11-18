
public class Cluster {
    public Cluster left = null;
    public Cluster right = null;
    public Cluster parent = null;
    public Blog blog = null;
    public double distance;


    Cluster(Blog b) { this.blog = b; }
    Cluster() { this.blog = null; }

    public Cluster merge(Cluster oldCluster, double distance) {
        Cluster newCluster = new Cluster();

        newCluster.left = this;
        parent = newCluster;
        newCluster.right = oldCluster;
        oldCluster.parent = newCluster;


        Blog newBlog = new Blog("");
        for (int i = 0; i < blog.blogWords.size(); i++) {
            Word wA = blog.blogWords.get(i);
            Word wB = oldCluster.blog.blogWords.get(i);

            double nCnt = (wA.getCount() + wB.getCount()) / 2.0;
            newBlog.blogWords.add(new Word(wA.getWord(), nCnt));
        }

        newCluster.blog = newBlog;
        newCluster.distance = distance;

        return newCluster;
    }
}
