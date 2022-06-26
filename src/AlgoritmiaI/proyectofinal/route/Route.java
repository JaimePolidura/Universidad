package AlgoritmiaI.proyectofinal.route;

public record Route(String id, int idOriginAirport, String codeOriginAirport, int idAirportDestination,
                    String codeAirportDestination, int codeshare, int numberOfStops, String planeType) {

    public static Route fromPrimitives(String[] arrayString){
        String id = arrayString[0];
        int idOriginAirport = Integer.parseInt(arrayString[1]);
        String codeOriginAirport = arrayString[2];
        int idAirportDestination = Integer.parseInt(arrayString[3]);
        String codeAirportDestination = arrayString[4];
        int codeshare = Integer.parseInt(arrayString[5]);
        int numberOfStops = Integer.parseInt(arrayString[6]);
        String planeType = arrayString[7];

        return new Route(id, idOriginAirport, codeOriginAirport, idAirportDestination, codeAirportDestination,
                codeshare, numberOfStops, planeType);
    }
}
