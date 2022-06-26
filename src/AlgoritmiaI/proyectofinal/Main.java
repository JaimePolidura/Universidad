package AlgoritmiaI.proyectofinal;

import AlgoritmiaI.proyectofinal.airport.AirportRepository;
import AlgoritmiaI.proyectofinal.airport.AirportsRepositoryInFile;
import AlgoritmiaI.proyectofinal.route.RouteRepositoryInFile;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AirportRepository airportsRepository = new AirportsRepositoryInFile(new File("src/main/resources/AirportData.txt"));
        RouteRepositoryInFile routesRepository = new RouteRepositoryInFile(new File("src/main/resources/RouteData.txt"));

        FlightsApplication flightsApplication = new FlightsApplication(airportsRepository, routesRepository);
    }
}
