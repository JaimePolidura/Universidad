package es.jaime.entregas.ejercicio1.ejercicio4;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public final class PersonTest {
    private Person person;

    @Before
    public void setUp() {
        final Calendar birthday = Calendar.getInstance();
        birthday.set(1982, 4, 15);
        person = new Person();
        person.setName("John");
        person.setLastName("Smith");
        person.setBirthday(birthday);
    }

    @Test
    public void testGetFullName() {
        final String expected = "John Smith";
        final String result = person.getFullName();
        assertEquals(expected, result);
    }
    @Test
    public void testGetAge() {
        Calendar date = Calendar.getInstance();
        date.set(2000, 1, 1);
        final int expected = 17;
        final int result = person.getAgeAtDate(date);
        assertEquals(expected, result);
    }
}
