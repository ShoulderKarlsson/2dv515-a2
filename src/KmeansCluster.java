import javafx.beans.binding.DoubleExpression;

import java.io.IOException;
import java.util.ArrayList;

public class KmeansCluster {
    ArrayList<Centroid> centroids = new ArrayList<>();


    // Amount of clusters
    private final int K = 2;

    public void init() {

        String fileContent = null;
        try {
            fileContent = FileHandler.readFileContent(System.getProperty("user.dir") + "/src/blogdata.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        doStuff(new BlogDataBucket(fileContent));
    }

    private void doStuff(BlogDataBucket bdb) {
        ArrayList<Blog> blogs = bdb.getBlogDataBucket();
        Rand r = new Rand(blogs);

        for (int i = 0; i < K; i++) centroids.add(r.generateRandomCentroid());

        boolean done = false;
        int iterations = 0;
        while (!done) {

            assignBlogsToCentroid(blogs);

            // Recalculate the center for the centroid
            centroids.forEach(Centroid::recalcCenter);

            done = true;
            for (Centroid c : centroids) {
                if (!c.isSame()) {
                    done = false;
                }
            }

            iterations++;
        }

        System.out.println("Iterations: " + iterations + ". Centroids: " + centroids.size());
    }


    private void assignBlogsToCentroid(ArrayList<Blog> blogs) {
        // Remove blogs connected to centroid from last run
        centroids.forEach(Centroid::reset);

        for (Blog b : blogs) {
            // Starting with arbitrary centroid as best candidate
            Centroid candidate = null;
            double closest = Double.MAX_VALUE;
            for (Centroid c : centroids) {
                double actualDistance = Calculations.calc_Pearson(b, c.b);
                if (actualDistance < closest) {
                    closest = actualDistance;
                    candidate = c;
                }
            }
            candidate.addBlog(b);
        }
    }
}
