package AlgoritmiaI.proyectofinal.airport;

import java.util.Objects;

public record Airport(int id , String name, String city, String country, String iata, String icao, double latitude, double longitude,
                      double height, String timeZone, String dst, String tz, String tipo, String source) {

    public static Airport fromPrimitives(String[] arrayString){
        int id = parseInteger(arrayString[0]);
        String name = parseString(arrayString[1]);
        String city = parseString(arrayString[2]);
        String country = parseString(arrayString[3]);
        String iata = parseString(arrayString[4]);
        String icao = parseString(arrayString[5]);
        double latitude = parseDouble(arrayString[6]);
        double longitude = parseDouble(arrayString[7]);
        double height = parseDouble(arrayString[8]);
        String timeZone = parseString(arrayString[9]);
        String dst = parseString(arrayString[10]);
        String tz = parseString(arrayString[11]);
        String tipo = parseString(arrayString[12]);
        String fuente = parseString(arrayString[13]);

        return new Airport(id, name, city, country, iata, icao, latitude, longitude,
                height, timeZone, dst, tz, tipo, fuente
        );
    }

    private static String parseString(String line){
        return line.replace("\n","");
    }

    private static int parseInteger(String idString){
        return Integer.parseInt(idString);
    }

    private static double parseDouble(String line){
        return Double.parseDouble(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id && Double.compare(airport.latitude, latitude) == 0
                && Double.compare(airport.longitude, longitude) == 0 &&
                Double.compare(airport.height, height) == 0 &&
                Objects.equals(name, airport.name) &&
                Objects.equals(city, airport.city) &&
                Objects.equals(country, airport.country) &&
                Objects.equals(iata, airport.iata) &&
                Objects.equals(icao, airport.icao) &&
                Objects.equals(timeZone, airport.timeZone) &&
                Objects.equals(dst, airport.dst) &&
                Objects.equals(tz, airport.tz) &&
                Objects.equals(tipo, airport.tipo) &&
                Objects.equals(source, airport.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, country, iata, icao, latitude,
                longitude, height, timeZone, dst, tz, tipo, source);
    }
}
