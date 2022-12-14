package es.jaime.proyectofinal.airport;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AirportRepository {
    List<Airport> getAirports() throws IOException;

    Optional<Airport> findByIata(String iata);
}
