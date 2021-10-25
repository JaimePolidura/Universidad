package AlgoritmiaI.clase.practica2;


import AlgoritmiaI.datastructures.queue.MyQueue;

import java.util.ArrayList;
import java.util.List;

public final class Parking {
    private final int capacity;
    private final MyQueue<Car> cars;

    private final MyQueue<Car> carsCheckedout;

    //Gates
    private final List<MyQueue<Car>> allGates;

    public Parking() {
        this(10);
    }

    public Parking (int capacity) {
        this.capacity = capacity;

        this.cars = new MyQueue<>();
        this.carsCheckedout = new MyQueue<>();
        this.allGates = new ArrayList<>();
        this.allGates.add(new MyQueue<>());
        this.allGates.add(new MyQueue<>());
        this.allGates.add(new MyQueue<>());
    }

    public void removeCar(){
        Car carToLeave = this.cars.dequeue();
        this.carsCheckedout.enqueue(carToLeave);

        if(cars.size() + 1 == capacity){
            callGatesMyQueuesSlotAvailable();
        }
    }

    private void callGatesMyQueuesSlotAvailable() {
        Car lastCarFoundInGateQueues = null;
        int lastCarFoundInGateQueuesIndex = -1;

        for (int i = 0; i < 3; i++) {
            MyQueue<Car> acutalQueue = allGates.get(i);
            boolean someCarsInTheQueue = !acutalQueue.isEmpty();

            if(someCarsInTheQueue){
                if(lastCarFoundInGateQueues == null){
                    lastCarFoundInGateQueues = acutalQueue.pick();
                    lastCarFoundInGateQueuesIndex = i;
                }else{
                    Car actualCar = acutalQueue.pick();
                    Car firstCar = compareTwoCars(lastCarFoundInGateQueues, actualCar);

                    if(firstCar == acutalQueue.pick()){
                        lastCarFoundInGateQueuesIndex = i;
                    }

                    this.cars.enqueue(firstCar);
                    this.allGates.get(lastCarFoundInGateQueuesIndex).dequeue();

                    return;
                }
            }
        }

        boolean onlyOneCarFound = lastCarFoundInGateQueues != null;

        if(onlyOneCarFound){
            this.cars.enqueue(lastCarFoundInGateQueues);
            this.allGates.get(lastCarFoundInGateQueuesIndex).dequeue();
        }
    }

    private Car compareTwoCars(Car car1, Car car2) {
        return car1.compareTo(car2) > 0 ?
                car1 :
                car2;
    }

    public void addCarToGate1(Car car) {
        addCarToGate(car, 1);
    }

    public void addCarToGate2(Car car) {
        addCarToGate(car, 2);
    }

    public void addCarToGate3(Car car) {
        addCarToGate(car, 3);
    }

    private void addCarToGate(Car car, int gateNumber){
        if(slotsAvailable()){
            this.cars.enqueue(car);
        }else{
            this.allGates.get(gateNumber - 1).enqueue(car);
        }
    }

    public MyQueue<Car> getCarsInGate1() {
        return this.allGates.get(0);
    }

    public MyQueue<Car> getCarsInGate2() {
        return this.allGates.get(1);
    }

    public MyQueue<Car> getCarsInGate3() {
        return this.allGates.get(2);
    }

    private boolean slotsAvailable () {
        return this.cars.size() < capacity;
    }

    public MyQueue<Car> getCars() {
        return cars;
    }

    public MyQueue<Car> getCheckouts () {
        return carsCheckedout;
    }

    public int getCapacity() {
        return capacity;
    }
}
