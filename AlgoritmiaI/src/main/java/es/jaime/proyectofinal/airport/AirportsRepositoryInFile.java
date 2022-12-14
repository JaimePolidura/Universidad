package es.jaime.proyectofinal.airport;

import es.jaime.proyectofinal.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class AirportsRepositoryInFile implements AirportRepository {
    private List<Airport> airports;
    private final File source;

    public AirportsRepositoryInFile(File source) throws IOException {
        this.airports = new ArrayList<>();
        this.source = source;

        this.airports = loadFromFile(this.source);
    }

    private List<Airport> loadFromFile(File file) throws IOException {
        List<Airport> allAirportsFromFile = new ArrayList<>();
        List<String> rawTextLines = Utils.getLinesFrom(source);
        int totalAirportsDiscarted = 0;

        for (String rawTextLine : rawTextLines) {
            try{
                allAirportsFromFile.add(parseFromCSVToAirport(rawTextLine));
            }catch (Exception e){
                totalAirportsDiscarted++;
            }
        }

        System.out.println("Discarted " + totalAirportsDiscarted + " airports");

        return allAirportsFromFile;
    }

    @Override
    public List<Airport> getAirports() {
        return this.airports;
    }

    @Override
    public Optional<Airport> findByIata(String iata) {
        return this.airports.stream()
                .filter(airport -> airport.iata().equalsIgnoreCase(iata))
                .findFirst();
    }

    private Airport parseFromCSVToAirport(String rawTextLine) {
        String[] primitiveArray = rawTextLine.split(",");
        String[] primitiveArrayParsed = removeQuotes(primitiveArray);

        return Airport.fromPrimitives(primitiveArrayParsed);
    }

    private String[] removeQuotes(String[] primitiveArray){
        return Arrays.stream(primitiveArray)
                .map(value -> Utils.removeCharacters(value, '"'))
                .toList()
                .toArray(new String[0]);
    }
}
