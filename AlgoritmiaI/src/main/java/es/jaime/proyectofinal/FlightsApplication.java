package es.jaime.proyectofinal;

import es.jaime.proyectofinal.airport.AirportRepository;
import es.jaime.proyectofinal.cli.FlightsApplicationCli;
import es.jaime.proyectofinal.graph.AirportsGraphCreator;
import es.jaime.proyectofinal.graph.GraphDisplayer;
import es.jaime.proyectofinal.route.RoutesRepository;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;

public final class FlightsApplication {
    private final Graph graph;
    private final GraphDisplayer graphDisplayer;
    private final FlightsApplicationCli flightsApplicationCli;

    public FlightsApplication(AirportRepository airportRepository, RoutesRepository routesRepository) throws IOException {
        this.graph = (new AirportsGraphCreator(airportRepository, routesRepository)).createGraph();
        this.graphDisplayer = new GraphDisplayer(graph);
        this.flightsApplicationCli = new FlightsApplicationCli(System.in);

        this.registerCommands();
        this.showWelcomeMessage();

        this.flightsApplicationCli.startListeningForCommands();
    }

    private void showWelcomeMessage(){
        this.showWelcomeMessage(
                "             RUNNING",
                "stop",
                "sroute <iata from> <iata to>"
        );
    }

    private void showWelcomeMessage(String... lines){
        System.out.println("--------------------------------------");
        Arrays.stream(lines).forEach(System.out::println);
        System.out.println("--------------------------------------");
    }

    private void registerCommands(){
        this.flightsApplicationCli.registerCommand("stop", args -> System.exit(-1));
        this.flightsApplicationCli.registerCommand("sroute", args -> shortestRoute(args[0], args[1]));
    }

    public void shortestRoute(String iataFrom, String iataTo){
        this.ensureIataExists(iataFrom, iataTo);

        Dijkstra shortestRoute = computeShortestRoute(iataFrom, iataTo);

        this.graphDisplayer.hideNodes();
        this.graphDisplayer.hideEdges();

        this.showEdges(shortestRoute, iataTo);
        this.showNodes(shortestRoute, iataTo);

        System.out.println("Path found");
    }

    private void ensureIataExists(String... iatas) {
        for (String iata : iatas)
            if(this.graph.getNode(iata) == null)
                throw new NullPointerException(format("Node with iata: %s not found", iata));
    }

    private Dijkstra computeShortestRoute(String iataFrom, String iataTo){
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

        dijkstra.init(graph);
        dijkstra.setSource(iataFrom);
        dijkstra.setTarget(iataTo);
        dijkstra.compute();

        return dijkstra;
    }

    private void showNodes(Dijkstra shortestRoute, String iataTo) {
        for(Node node : shortestRoute.getPathNodes(graph.getNode(iataTo)))
            this.graphDisplayer.showNode(node);
    }

    private void showEdges(Dijkstra dijkstra, String iataTo){
        for(Edge edge : dijkstra.getPathEdges(graph.getNode(iataTo)))
            this.graphDisplayer.showEdge(edge);
    }
}
