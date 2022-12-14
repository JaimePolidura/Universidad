package es.jaime.clase.practica2;

import java.util.Comparator;

public final class Car implements Comparable<Car>{
    private int plate;
    private int passengers;

    public Car () {}

    public Car(int plate, int passengers) {
        this.plate = plate;
        this.passengers = passengers;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getPlate() {
        return plate;
    }

    public int getPassengers() {
        return passengers;
    }

    @Override
    public int compareTo(Car other) {
        return Comparator.comparing(Car::getPassengers)
                .thenComparingInt(Car::getPlate)
                .compare(this, other);
    }

}
