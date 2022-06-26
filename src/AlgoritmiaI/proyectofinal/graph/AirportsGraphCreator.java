package AlgoritmiaI.proyectofinal.graph;

import AlgoritmiaI.proyectofinal.airport.Airport;
import AlgoritmiaI.proyectofinal.airport.AirportRepository;
import AlgoritmiaI.proyectofinal.route.Route;
import AlgoritmiaI.proyectofinal.route.RoutesRepository;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.*;

public class AirportsGraphCreator {
    private Graph graph;
    private final AirportRepository airportRepository;
    private final RoutesRepository routesRepository;

    public AirportsGraphCreator(AirportRepository airportRepository, RoutesRepository routesRepository) {
        this.airportRepository = airportRepository;
        this.routesRepository = routesRepository;
    }

    public Graph createGraph() throws IOException {
        this.graph = new SingleGraph("graph");
        addNodes();
        addEdges();

        return graph;
    }

    public Graph getGraph() {
        return graph;
    }

    private void addNodes() throws IOException {
        List<Airport> airports = airportRepository.getAirports();
        int totalNodesDiscarted = 0;

        for (Airport airport : airportRepository.getAirports()) {
            try{
                if(airport.iata().equalsIgnoreCase("\\N")){
                    continue;
                }

                graph.addNode(airport.iata());
            }catch (Exception e) {
                totalNodesDiscarted++;
            }
        }

        System.out.println("Discarted " + totalNodesDiscarted + " nodes");
    }

    private void addEdges() throws IOException {
        List<Route> routes = routesRepository.getRoutes();
        int edgesDiscarted = 0;

        for (Route route : routes) {
            try{
                String iataFrom = route.codeOriginAirport();
                String iataTo = route.codeAirportDestination();

                double distance = calculateDistanceFromTwoAirports(route.codeOriginAirport(), route.codeAirportDestination());

                graph.addEdge(iataFrom + "-" + iataTo, String.valueOf(iataFrom), String.valueOf(iataTo)).setAttribute("length", distance);
            }catch (Exception e) {
                edgesDiscarted++;
            }
        }

        System.out.println("Discarted " + edgesDiscarted + " edges");
    }

    private double calculateDistanceFromTwoAirports(String iataFrom, String iataTo){
        Airport airportFrom = airportRepository.findByIata(iataFrom).get();
        Airport airportTo = airportRepository.findByIata(iataTo).get();

        double latitudeFrom = airportFrom.latitude();
        double longitudFrom = airportFrom.longitude();
        double latitudeTo = airportTo.latitude();
        double longitudTo = airportFrom.longitude();

        return 2* asin(sqrt(pow(sin(latitudeFrom - longitudFrom /2), 2) + cos(latitudeFrom)* cos(longitudFrom)* pow(sin(latitudeTo - longitudTo /2), 2)));
    }
}
