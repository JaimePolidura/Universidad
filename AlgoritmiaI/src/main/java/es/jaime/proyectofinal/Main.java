package es.jaime.proyectofinal;

import es.jaime.proyectofinal.airport.AirportRepository;
import es.jaime.proyectofinal.airport.AirportsRepositoryInFile;
import es.jaime.proyectofinal.route.RouteRepositoryInFile;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        AirportRepository airportsRepository = new AirportsRepositoryInFile(new File("src/main/resources/AirportData.txt"));
        RouteRepositoryInFile routesRepository = new RouteRepositoryInFile(new File("src/main/resources/RouteData.txt"));

        FlightsApplication flightsApplication = new FlightsApplication(airportsRepository, routesRepository);
    }
}
