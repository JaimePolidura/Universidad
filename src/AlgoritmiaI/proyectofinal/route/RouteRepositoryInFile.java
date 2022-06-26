package AlgoritmiaI.proyectofinal.route;

import AlgoritmiaI.proyectofinal.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteRepositoryInFile implements RoutesRepository{
    private List<Route> routes;
    private final File source;

    public RouteRepositoryInFile(File source) throws IOException {
        this.routes = new ArrayList<>();
        this.source = source;

        this.routes = loadFromFile(source);
    }

    private List<Route> loadFromFile(File file) throws IOException {
        List<Route> routes = new ArrayList<>();
        List<String> rawTextLines = Utils.getLinesFrom(source);
        int totalNumberOfRoutesDiscarted = 0;

        for (String rawTextLine : rawTextLines) {
            try{
                routes.add(parseFromCSVToRoute(rawTextLine));
            }catch (Exception e){
                totalNumberOfRoutesDiscarted++;
            }
        }

        System.out.println("Discarted " + totalNumberOfRoutesDiscarted + " routes");

        return routes;
    }

    @Override
    public List<Route> getRoutes() throws IOException {
        return this.routes;
    }

    private Route parseFromCSVToRoute(String rawTextLine) {
        String[] primitiveArray = rawTextLine.replace(",,", ",").split(",");
        String[] primitiveArrayParsed = Arrays.stream(primitiveArray)
                .map(value -> Utils.removeCharacters(value, '"'))
                .toList()
                .toArray(new String[0]);

        return Route.fromPrimitives(primitiveArrayParsed);
    }
}
