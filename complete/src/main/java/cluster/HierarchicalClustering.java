package cluster;
import java.util.ArrayList;

class HierarchicalClustering {
    private ArrayList<Cluster> clusters = null;
    private ArrayList<Blog> blogs = null;

    HierarchicalClustering(BlogDataBucket dataBucket) {
        blogs = dataBucket.getBlogDataBucket();
        generateStartingClusters();

        while (clusters.size() > 1) generateHierCluster();
    }

    String getClusterTree() {
        HTMLTree htmlTree = new HTMLTree();
        return htmlTree.generateTree(clusters);
    }

    public void makeHierCluster() {
        while(clusters.size() > 1) generateHierCluster();
    }



    private void generateHierCluster() {
        double closest = Double.MAX_VALUE;
        Cluster bestA = null;
        Cluster bestB = null;

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = i + 1; j < clusters.size(); j++) {
                Cluster clusterA = clusters.get(i);
                Cluster clusterB = clusters.get(j);

                double candidate = Calculations.calc_Pearson(clusterA.blog, clusterB.blog);
                if (candidate < closest) {
                    closest = candidate;
                    bestA = clusterA;
                    bestB = clusterB;
                }
            }
        }

        if (bestA != null) {
            Cluster mergedCluster = bestA.merge(bestB, closest);
            clusters.add(mergedCluster);
            clusters.remove(bestA);
            clusters.remove(bestB);
        }
    }


    private void generateStartingClusters() {
        clusters = new ArrayList<>();
        for (Blog b : blogs) {
            clusters.add(new Cluster(b));
        }
    }

}
