package hello;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping(value = "/kmeans" /*produces = MediaType.APPLICATION_JSON_VALUE*/)
    @ResponseBody
    public String kmeans() {
        KmeansCluster cluster = new KmeansCluster();
        ArrayList<Centroid> clusters = cluster.doCluster();
        StringBuilder value = new StringBuilder();

        for (Centroid c : clusters) {
            value.append("<ul>");
            value.append("<h2>Centroid</h2>");
            for (Blog b : c.cluster) {
                value
                        .append("<li>")
                        .append("<h3>")
                        .append(b.blogName)
                        .append("</h3>")
                        .append("</li>");
            }
            value
                    .append("</ul>")
                    .append("<hr/>");

        }


        return value.toString();
    }
}
