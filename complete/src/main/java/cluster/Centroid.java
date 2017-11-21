package cluster;

import java.util.ArrayList;

class Centroid {
    ArrayList<Blog> cluster = new ArrayList<>();
    private ArrayList<Blog> previousCollection;

    Blog b = null;

    Centroid(Blog b) {
        this.b = b;
    }

    /**
     * Recalculates position for the centroid
     * The centroid should be positioned at the center
     * of the cluster
     */
    void recalcCenter() {
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
    boolean isSame() {
        return cluster.containsAll(previousCollection) &&
               previousCollection.containsAll(cluster);
    }

    /**
     * Connect a blog to the specific cluster
     * @param b
     */
    void addBlog(Blog b) {
        cluster.add(b);
    }
}
