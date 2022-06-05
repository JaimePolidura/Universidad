package AlgoritmiaII.proyectofinal.app;

import AlgoritmiaII.proyectofinal.game.CovidLogic;
import AlgoritmiaII.proyectofinal.game.Person;
import AlgoritmiaII.proyectofinal.game.VirusParameters;
import AlgoritmiaII.proyectofinal.ui.CovidGameWindow;
import AlgoritmiaII.proyectofinal.utils.RandomUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CovidGame {
    public static final int ROWS = 16;
    public static final int COLUMNS = 16;
    public static final int MSECONDS_PER_DAY = 100;
    public static final double VIRUS_TRANSMISSION_PERCENT = 5;
    public static final int VIRUS_TIMELIFE_DAYS = 30;
    public static final int MIN_NEIGH_TO_BE_SURROUNDED = 3;
    public static final int MAX_INFECTION_DISTANCE = 5;

    public static void main(String[] args) throws InterruptedException {
        final CovidGameWindow game = new CovidGameWindow();
        game.setRowsAndColumns(ROWS, COLUMNS);

        final VirusParameters virusParameters = new VirusParameters(
                VIRUS_TRANSMISSION_PERCENT,
                VIRUS_TIMELIFE_DAYS,
                MIN_NEIGH_TO_BE_SURROUNDED,
                MAX_INFECTION_DISTANCE
        );

        final CovidLogic covidLogic = new CovidLogic(virusParameters);

        List<List<Person>> population = initializePopulation(ROWS, COLUMNS);

        game.setCellStates(toLinearList(population));

        while (atLeastOnePersonInfected(population)) {
            List<List<Person>> newPopulation = covidLogic.advanceDay(population);

            final List<Person> cellStates = newPopulation.stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toUnmodifiableList());

            game.setCellStates(cellStates);

            Thread.sleep(MSECONDS_PER_DAY);

            printForm(population, newPopulation);

            population = newPopulation;
        }

        System.out.println("FINAL");
    }

    private static void printForm(List<List<Person>> prevPopulation, List<List<Person>> actualPopulation){
        List<Person> linearListPrevPopulation = toLinearList(prevPopulation);
        int numberOfInfectedPersonsPrevPopulation = filterAndCount(linearListPrevPopulation, Person::isInfected);
        int numberOfNotInfectedPersonsPrevPopulation = filterAndCount(linearListPrevPopulation, Person::isNotInfected);
        int numberOfDeathPersonsPrevPopulation = filterAndCount(linearListPrevPopulation, Person::isDeath);

        List<Person> linearListActualPopulation = toLinearList(actualPopulation);
        int numberOfInfectedPersonsActualPopulation = filterAndCount(linearListActualPopulation, Person::isInfected);
        int numberOfNotInfectedPersonsActualPopulation = filterAndCount(linearListActualPopulation, Person::isNotInfected);
        int numberOfDeathPersonsActualPopulation = filterAndCount(linearListActualPopulation, Person::isDeath);

        System.out.println("-------------------------");
        System.out.println("Cambio de infectados: " + (numberOfInfectedPersonsActualPopulation - numberOfInfectedPersonsPrevPopulation));
        System.out.println("Cambio de muertos: " + (numberOfDeathPersonsActualPopulation - numberOfDeathPersonsPrevPopulation));
        System.out.println("Cambio de sanos: " + (numberOfNotInfectedPersonsActualPopulation - numberOfNotInfectedPersonsPrevPopulation));
    }

    public static <T> int filterAndCount(List<T> list, Predicate<T> filter){
        return list.stream()
                .filter(filter)
                .toList()
                .size();
    }

    private static boolean atLeastOnePersonInfected(List<List<Person>> population) {
        return toLinearList(population).stream().
                anyMatch(Person::isInfected);
    }

    private static List<Person> toLinearList(List<List<Person>> persons){
        return persons.stream()
                .flatMap(Collection::stream)
                .toList();
    }

    private static List<List<Person>> initializePopulation(int rows, int columns) {
        return Stream.iterate(0, i -> i < rows, i -> i + 1)
                .map(i -> Stream.iterate(0, j -> j < columns, j -> j + 1)
                        .map(j -> j == columns / 2 && i == rows / 2)
                        .map(infected -> infected ? Person.infected() : Person.notInfected())
                        .map(person -> RandomUtils.matchesPossibility(5) ? person.masked() : person)
                        .toList())
                .toList();
    }

}
