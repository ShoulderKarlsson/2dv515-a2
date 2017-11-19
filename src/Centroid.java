import java.util.ArrayList;

public class Centroid {
    ArrayList<Blog> blogs = new ArrayList<>();
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

    }

    /**
     * Removing everything from the
     * centroid so it is not in relation to any blogs
     */
    void reset() {
        previousCollection = blogs;
        blogs = new ArrayList<>();
    }

    /**
     * Determines whether the centroid was moved or not
     */
    public boolean isSame() {
        return blogs.containsAll(previousCollection) &&
               previousCollection.containsAll(blogs);
    }

    /**
     * Connect a blog to the specific cluster
     * @param b
     */
    public void addBlog(Blog b) {
        blogs.add(b);
    }

    // Leaking ref, but does not care atm.
    public ArrayList<Blog> getBlogs() {
        return blogs;
    }
}
