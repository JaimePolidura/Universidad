package es.jaime.proyectofinal.graph;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;

public final class GraphDisplayer {
    private final Graph graph;
    private final Viewer viewer;

    public GraphDisplayer(Graph graph) {
        System.setProperty("org.graphstream.ui", "swing");

        graph.setAttribute("ui.stylesheet", getCSS());

        this.graph = graph;
        this.viewer = graph.display();

        this.hideEdges();
        this.hideNodes();
    }

    public void hideNodes(){
        this.graph.nodes().forEach(node -> node.setAttribute("ui.hide"));
    }

    public void hideEdges(){
        this.graph.edges().forEach(edge -> edge.setAttribute("ui.hide"));
    }

    public void showNode(Node node){
        node.setAttribute("ui.class", "visible");
        node.setAttribute("ui.label", node.getId());
        node.removeAttribute("ui.hide");
    }

    public void showEdge(Edge edge){
        edge.setAttribute("ui.class", "visible");
        edge.removeAttribute("ui.hide");
    }

    private String getCSS(){
        return """
                 edge {
                     size: 2;
                 }
                 
                 node {
                     fill-color: red;
                     size: 12;
                     text-alignment: at-right;
                     text-style: bold;
                     text-size: 20;
                     text-color: blue;
                 }
                """;
    }

}
