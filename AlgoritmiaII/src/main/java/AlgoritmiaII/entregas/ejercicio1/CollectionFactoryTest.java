package AlgoritmiaII.entregas.ejercicio1;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class CollectionFactoryTest {
    private CollectionFactory<Person> factory;
    private Person[] persons;

    @Before
    public void setUp() throws Exception {
        factory = new CollectionFactory<Person>();
        persons = new Person[] {
                new Person(10, "Juan"),
                new Person(50, "Patricia"),
                new Person(1, "Peter"),
                new Person(6, "John"),
                new Person(3, "Daniel")
        };
    }

    @Test
    public void testCreateAscSortedCollection() {
        final Collection<Person> collection = factory.createAscSortedCollection();
        collection.addAll(Arrays.asList(persons));
        Object[] expected = {persons[2], persons[4], persons[3], persons[0],
                persons[1]};

        assertArrayEquals(expected, collection.toArray());
    }

    @Test
    public void testCreateDescSortedCollection() {
        final Collection<Person> collection =
                factory.createDescSortedCollection();
        for (final Person person : persons) {
            collection.add(person);
        }
        Object[] expected = {persons[1], persons[0], persons[3], persons[4],
                persons[2]};
        assertArrayEquals(expected, collection.toArray());
    }

}
