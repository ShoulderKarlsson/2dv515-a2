import java.io.IOException;
import java.util.ArrayList;

public class KmeansCluster {
    ArrayList<Centroid> centroids = new ArrayList<>();


    // Amount of clusters
    private final int K = 3;

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

        for (int i = 0; i <= K; i++) centroids.add(r.generateRandomCentroid());


//        boolean done = false;
//        int iterations = 0;
//
//        while(!done) {
//
//            // Recalculate the center for the centroid
//            centroids.forEach(Centroid::recalcCenter);
//
//            done = true;
//            for (Centroid c : centroids) {
//                if (!c.isSame()) {
//                    done = false;
//                }
//            }
//
//            iterations++;
//        }
//
//        System.out.println("Iterations: " + iterations + ". Centroids: " + centroids.size());
    }


    private void assignBlogsToCentroid(ArrayList<Blog> blogs) {
        // Remove blogs connected to centroid from last run
        centroids.forEach(Centroid::reset);

        for (Blog blog : blogs) {
            // Arbitrary centroid as initial candidate for best centroid
            Centroid candidate = centroids.get(0);
            double bestDistance = Double.MAX_VALUE;

            for (Centroid centroid : centroids) {

                // This should calculate the distance between a blog and a centroid?
                double distance = Calculations.calcPearson2();


                if (distance < bestDistance) {
                    // Found closer centroid, this is not candidate
                    candidate = centroid;

                    // Update the best distance to the distance
                    // that was smaller than previous distance.
                    bestDistance = distance;
                }
            }

            candidate.addBlog(blog);
        }

    }
}
