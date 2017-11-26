package cluster;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    BlogDataBucket dataBucket = null;

    MainController() {
        String fileContent = null;
        try {
            fileContent = FileHandler.readFileContent(ResourceUtils.getFile("classpath:blogdata.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataBucket = new BlogDataBucket(fileContent);
    }


    @RequestMapping(value = "/kmeans")
    @ResponseBody
    public String kmeans() {
        KmeansCluster cluster = new KmeansCluster(dataBucket);
        ArrayList<Centroid> clusters = cluster.makeKmeansCluster();
        StringBuilder value = new StringBuilder();

        for (Centroid c : clusters) {
            value.append("<ul>");
            value.append("<h2>Centroid</h2>");
            for (Blog b : c.cluster) {
                value
                        .append("<li>")
                        .append("<p>")
                        .append(b.blogName)
                        .append("</p>")
                        .append("</li>");
            }
            value
                    .append("</ul>")
                    .append("<hr/>");

        }

        return value.toString();
    }

    @RequestMapping(value = "/heir")
    @ResponseBody
    public String hier() {
        HierarchicalClustering hc = new HierarchicalClustering(dataBucket);
        hc.makeHierCluster();

        return hc.getClusterTree();
    }
}
