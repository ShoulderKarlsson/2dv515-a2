import java.util.ArrayList;

public class Centroid {
    ArrayList<Blog> cluster = new ArrayList<>();
    ArrayList<Blog> previousCollection;

    // Using the same datastructure to store the centroids
    // "position" as I store the blog entries.
    Blog b = null;

    Centroid(Blog b) {
        this.b = b;
    }

    /**
     * Recalculates position for the centroid
     * The centroid should be positioned at the center
     * of the cluster
     */
    public void recalcCenter() {
        for (int i = 0; i < b.blogWords.size(); i++) {
            double avg = 0.0;
            for (Blog b : cluster) { avg += b.blogWords.get(i).getCount(); }
            avg /= (double)cluster.size();
            b.blogWords.get(i).count = avg;
        }
    }

    /**
     * Removing everything from the
     * centroid so it is not in relation to any blogs
     */
    void reset() {
        previousCollection = cluster;
        cluster = new ArrayList<>();
    }

    /**
     * Determines whether the centroid was moved or not
     */
    public boolean isSame() {
        return cluster.containsAll(previousCollection) &&
               previousCollection.containsAll(cluster);
    }

    /**
     * Connect a blog to the specific cluster
     * @param b
     */
    public void addBlog(Blog b) {
        cluster.add(b);
    }
}
