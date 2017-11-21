package cluster;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KmeansController {
    @RequestMapping(value = "/kmeans")
    @ResponseBody
    public String kmeans() {
        KmeansCluster cluster = new KmeansCluster();
        ArrayList<Centroid> clusters = cluster.init();
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
}
