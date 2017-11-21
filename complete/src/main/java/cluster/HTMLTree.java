package cluster;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class HTMLTree {
    ArrayList<String> html = new ArrayList<>();

    public String generateTree(ArrayList<Cluster> clusters) {
        html.add("<ul>");
        html.add("</ul>");
        addNodes(1, clusters.get(0));
        StringBuilder treeRepresentation = new StringBuilder();

        for (String s : html) {
            treeRepresentation.append(s).append("\n");
        }

        return treeRepresentation.toString();
    }


    public void addNodes(int i, Cluster c) {
        if (c.right != null) {
            String blg = c.right.toString();
            if (blg.equals("")) {
                html.add(i, "<li><ul>");
                html.add(i+1, "</ul></li>");
            } else {
                blg = blg.replaceAll("\"", "'");
                html.add(i, "<li data-jstree='{\"disabled\": true}'>" + blg + "</li>");
            }

            addNodes(i + 1, c.right);
        }

        if (c.left != null) {
            String blg = c.left.toString();
            if (blg.equals("")) {
                html.add(i, "<li><ul>");
                html.add(i + 1, "</ul></li>");
            } else {
                blg = blg.replaceAll("\"", "'");
                html.add(i, "<li data-jstree='{\"disabled\": true}'>" + blg + "</li>");
            }

            addNodes(i + 1, c.left);
        }
    }

    public void addNodes(DefaultMutableTreeNode tnode, Cluster c) {
        if (c.left != null) {
            DefaultMutableTreeNode nNode = new DefaultMutableTreeNode(c.left.toString());
            tnode.add(nNode);
            addNodes(nNode, c.left);
        }

        if (c.right != null) {
            DefaultMutableTreeNode nNode = new DefaultMutableTreeNode(c.right.toString());
            tnode.add(nNode);
            addNodes(nNode, c.right);
        }
    }
}
