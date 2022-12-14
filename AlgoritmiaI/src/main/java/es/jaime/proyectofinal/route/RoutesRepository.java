package es.jaime.proyectofinal.route;

import java.io.IOException;
import java.util.List;

public interface RoutesRepository {
    List<Route> getRoutes() throws IOException;
}
