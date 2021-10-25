package AlgoritmiaI.clase.practica2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingTest {
    private Parking parking;

    @BeforeEach
    private void setup() {
        parking = new Parking();
    }

    @Test
    public void initializeParking() {
        assertEquals(10, parking.getCapacity());
        assertEquals(0, parking.getCars().size());

        assertArrayEquals(new Car[0], parking.getCarsInGate1().listData());
        assertArrayEquals(new Car[0], parking.getCarsInGate2().listData());
        assertArrayEquals(new Car[0], parking.getCarsInGate3().listData());

        assertArrayEquals(new Car[0], parking.getCheckouts().listData());
    }

    @Test
    public void fillTheParking() {
        fillParking(parking);

        int expectedSize = parking.getCapacity();

        assertEquals(expectedSize, parking.getCars().size());
        assertTrue(parking.getCarsInGate1().isEmpty());
        assertTrue(parking.getCarsInGate2().isEmpty());
        assertTrue(parking.getCarsInGate3().isEmpty());
    }

    @Test
    public void addCarsToGatesWhenParkingIsFull() {
        fillParking(parking);

        Car car1 = createCar(1001, 3);
        Car car2 = createCar(1002, 3);
        Car car3 = createCar(1003, 3);

        parking.addCarToGate1(car1);
        parking.addCarToGate2(car2);
        parking.addCarToGate3(car3);

        assertArrayEquals(new Car[]{car1}, parking.getCarsInGate1().listData());
        assertArrayEquals(new Car[]{car2}, parking.getCarsInGate2().listData());
        assertArrayEquals(new Car[]{car3}, parking.getCarsInGate3().listData());
    }

    @Test
    public void oneCarGetOut() {
        fillParking(parking);

        int expectedSize = parking.getCapacity() - 1;

        parking.removeCar();

        assertEquals(expectedSize, parking.getCars().size());
        assertEquals(1, parking.getCheckouts().size());
    }

    @Test
    public void letCarInWhenOneCarGoesOut() {
        fillParking(parking);

        Car extraCar = createCar(1000, 4);

        parking.addCarToGate1(extraCar);

        parking.removeCar();

        int expectedSize = parking.getCapacity();

        assertEquals(expectedSize, parking.getCars().size());
        assertTrue(parking.getCarsInGate1().isEmpty());
    }

    @Test
    public void letCarInFirstByPassengers() {
        fillParking(parking);

        Car car1 = createCar(1001, 3);
        Car car3 = createCar(1003, 3);
        Car nextCarToGetIntoParking = createCar(1002, 4);

        parking.addCarToGate1(car1);
        parking.addCarToGate2(nextCarToGetIntoParking);
        parking.addCarToGate3(car3);

        parking.removeCar();

        assertArrayEquals(new Car[]{car1}, parking.getCarsInGate1().listData());
        assertArrayEquals(new Car[]{car3}, parking.getCarsInGate3().listData());

        assertTrue(parking.getCarsInGate2().isEmpty());
    }

    @Test
    public void letCarInFirstByPlateIfSamePassengers() {
        fillParking(parking);

        Car car1 = createCar(1001, 3);
        Car car3 = createCar(1003, 3);
        Car nextCarToGetIntoParking = createCar(1020, 3);

        parking.addCarToGate1(car1);
        parking.addCarToGate2(nextCarToGetIntoParking);
        parking.addCarToGate3(car3);

        parking.removeCar();

        assertArrayEquals(new Car[]{car1}, parking.getCarsInGate1().listData());
        assertArrayEquals(new Car[]{car3}, parking.getCarsInGate3().listData());

        assertTrue(parking.getCarsInGate2().isEmpty());
    }

    private Car createCar(int plate, int numberOfPassengers) {
        Car newCar = new Car();
        newCar.setPlate(plate);
        newCar.setPassengers(numberOfPassengers);

        return newCar;
    }

    private void fillParking(Parking parking) {
        int passengers = 1;

        for (short i = 0; i < parking.getCapacity(); ++i) {
            parking.addCarToGate1(createCar(i, passengers));
            passengers = passengers > 5 ? 1 : passengers + 1;
        }
    }
}
