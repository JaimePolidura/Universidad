package AlgoritmiaII.proyectofinal.game;


import AlgoritmiaII.proyectofinal.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class CovidLogic {
    private final VirusParameters parameters;

    public CovidLogic(VirusParameters parameters) {
        this.parameters = parameters;
    }

    public List<List<Person>> advanceDay(List<List<Person>> initialPopulation) {
        var newPopulation = getPersonsCellFromPopulation(initialPopulation)
                .map(personCell -> changeStateToSurroundedIfSurrounded(personCell, initialPopulation))
                .map(personCell-> personCell.isInfected() ?
                        increaseDaysSinceInfection(personCell) :
                        personCell.canBeInfected() ? infectIfSurrounded(initialPopulation, personCell) : personCell)
                .toList();

        return toBiDimensionalList(newPopulation, initialPopulation.size());
    }

    private PersonCell changeStateToSurroundedIfSurrounded(PersonCell personCell, List<List<Person>> initialPopulation) {
        final int numberOfInfectedNeighbours = this.getNeighbours(initialPopulation, personCell.x, personCell.y(), parameters.maxInfectionDistance)
                .stream()
                .filter(NeighbourPersonCell::isInfected)
                .toList()
                .size();

        return numberOfInfectedNeighbours == this.parameters.minNeighToBeSurrounded && personCell.canBeInfected() ?
                personCell.withState(PersonState.STATE_SURROUNDED) :
                personCell;
    }

    private PersonCell infectIfSurrounded(List<List<Person>> initialPopulation, PersonCell personCell) {
        return this.getNeighbours(initialPopulation, personCell.x, personCell.y, 1).stream()
                .filter(NeighbourPersonCell::isInfected)
                .sorted() //Lo ordenamos por cercania
                .findFirst() //Sacamos el que mas cera esta
                .map(neighbour -> infect(personCell, neighbour)) //Se ha encontrado a un vecino infectado
                .orElse(personCell); //No hay cambios, no se ha encontrado a vecinos infectados
    }

    public PersonCell infect(PersonCell personCell, NeighbourPersonCell neighbour) {
        double possibilityOfInfectionByNeighbour = calculatePossibilityOfInfection(personCell, neighbour);

        return RandomUtils.matchesPossibility(possibilityOfInfectionByNeighbour) ?
                personCell.infect():
                personCell;
    }

    private double calculatePossibilityOfInfection(PersonCell personCell, NeighbourPersonCell neighbour) {
        var bothAreMasked = personCell.isMasked() && neighbour.isMasked();
        var onlyOneIsMasked = personCell.isMasked() || neighbour.isMasked();

        if(bothAreMasked)
            return (parameters.transmissionPercent / 4) / neighbour.distance;
        else if(onlyOneIsMasked)
            return (parameters.transmissionPercent / 2) / neighbour.distance;
        else
            return parameters.transmissionPercent / neighbour.distance;
    }

    private List<List<Person>> toBiDimensionalList(List<PersonCell> personCells, int maxRows) {
        final List<List<Person>> toReturn = new ArrayList<>(maxRows);

        IntStream.range(0, maxRows).forEach(i -> toReturn.add(new ArrayList<>()));
        personCells.forEach(person -> toReturn.get(person.y).add(person.person));

        return toReturn;
    }

    public List<NeighbourPersonCell> getNeighbours(List<List<Person>> initialPopulation, int x, int y, int maxDistance){
        return getPersonsCellFromPopulation(initialPopulation)
                .filter(personCell -> Math.abs(personCell.x - x) + Math.abs(personCell.y - y) == 1)
                .map(person -> new NeighbourPersonCell(person,Math.abs(person.x - x) + Math.abs(person.y - y)))
                .toList();
    }

    private PersonCell increaseDaysSinceInfection(PersonCell person) {
        final var newPersonWithIncreasedDays = person.incrementDaysSinceInfection();
        final var canBeImmunized = newPersonWithIncreasedDays.getDaysSinceInfection() >= parameters.lifetimeInDays;

        return canBeImmunized ?
                newPersonWithIncreasedDays.withState(PersonState.IMMUNE) :
                canDie(newPersonWithIncreasedDays) ?
                        newPersonWithIncreasedDays.withState(PersonState.DEATH) :
                        newPersonWithIncreasedDays;
    }

    private boolean canDie(PersonCell newPersonWithIncreasedDays) {
        return RandomUtils.matchesPossibility(((double) newPersonWithIncreasedDays.getDaysSinceInfection())/10);
    }

    private Stream<PersonCell> getPersonsCellFromPopulation(List<List<Person>> initialPopulation) {
        return Stream.iterate(0, y -> y < initialPopulation.size(), y -> y + 1)
                .map(y -> Stream.iterate(0, x -> x < initialPopulation.get(y).size(), x -> x + 1)
                        .map(x -> new PersonCell(initialPopulation.get(y).get(x), x, y)))
                .flatMap(person -> person);
    }

    record NeighbourPersonCell(PersonCell personCell, int distance) implements Comparable<NeighbourPersonCell>{
        @Override
        public int compareTo(NeighbourPersonCell o) {
            return this.distance - o.distance;
        }

        public boolean isInfected(){
            return this.personCell.isInfected();
        }

        public boolean isMasked() {
            return this.personCell.isMasked();
        }
    }

    record PersonCell(Person person, int x, int y){
        public boolean isInfected(){
            return this.person.isInfected();
        }

        public boolean isMasked(){
            return this.person.isMasked();
        }

        public int getDaysSinceInfection(){
            return this.person.getDaysSinceInfection();
        }

        public PersonCell withState(PersonState state){
            return new PersonCell(person.withState(state), x, y);
        }

        public PersonCell incrementDaysSinceInfection(){
            return new PersonCell(person.incrementDaysSinceInfection(), x, y);
        }

        public PersonCell infect(){
            return new PersonCell(person.infect(), x, y);
        }

        public boolean canBeInfected() {
            return this.person.getState().isInfectable();
        }
    }
}
