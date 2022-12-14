package AlgoritmiaII.entregas.ejercicio1;

public class Person implements Comparable<Person>{
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Person o) {
        return this.id - o.getId();
    }
}
