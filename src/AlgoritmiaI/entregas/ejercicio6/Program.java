package AlgoritmiaI.entregas.ejercicio6;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Program {
    public static Graph createGraph() {
        Graph graph = new SingleGraph("Graph");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("Z");

        graph.addEdge("AB", "A", "B").setAttribute("length", 3);
        graph.addEdge("AC", "A", "C").setAttribute("length", 5);
        graph.addEdge("AE", "A", "E").setAttribute("length", 8);
        graph.addEdge("BD", "B", "D").setAttribute("length", 3);
        graph.addEdge("BE", "B", "E").setAttribute("length", 2);
        graph.addEdge("CE", "C", "E").setAttribute("length", 2);
        graph.addEdge("CF", "C", "F").setAttribute("length", 4);
        graph.addEdge("EF", "E", "F").setAttribute("length", 1);
        graph.addEdge("DE", "D", "E").setAttribute("length", 1);
        graph.addEdge("DZ", "D", "Z").setAttribute("length", 4);
        graph.addEdge("EZ", "E", "Z").setAttribute("length", 2);
        graph.addEdge("FZ", "F", "Z").setAttribute("length", 2);

        return graph;
    }

    public static String[] createMatrix(Graph graph) {
        List<String> result = new ArrayList<>();
        Dijkstra dijkstra = computeDijkstra(graph, "A", "Z");

        result.add("A (0, -)");
        for(int i = 1; i < graph.getNodeCount(); i++){
            Node actualNode = graph.getNode(i);
            Node closestNodeShortestPath = getNodeClosestNodeShortestPath(dijkstra, actualNode);

            result.add(format("%s (%s, %s)",
                    actualNode.getId(),
                    (int) dijkstra.getPathLength(actualNode),
                    closestNodeShortestPath.getId()));
        }

        return result.toArray(new String[0]);
    }

    private static Dijkstra computeDijkstra(Graph graph, String source, String target){
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

        dijkstra.init(graph);
        dijkstra.setSource(source);
        dijkstra.setTarget(target);
        dijkstra.compute();

        return dijkstra;
    }

    private static Node getNodeClosestNodeShortestPath(Dijkstra dijkstra, Node target){
        return dijkstra.getParent(target);
    }

}
