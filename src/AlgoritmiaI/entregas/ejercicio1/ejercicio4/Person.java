package AlgoritmiaI.entregas.ejercicio1.ejercicio4;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public final class Person {
    private String name;
    private String lastName;
    private Calendar birthday;
    private String country;

    public Person(String name, String lastName, Calendar birthday, String country) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = country;
    }

    public Person() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public int getAgeAtDate(Calendar date) {
        long differenceMillis = Math.abs(date.getTimeInMillis() - birthday.getTimeInMillis());

        return (int) (TimeUnit.DAYS.convert(differenceMillis, TimeUnit.MILLISECONDS) / 365);
    }
}
