package AlgoritmiaI.entregas.ejercicio4;

public final class Plane {
    private int id;
    private String airline;
    private String destination;

    public Plane () {}

    public Plane(int id, String airline, String destination) {
        this.id = id;
        this.airline = airline;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

    public String getDestination() {
        return destination;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
