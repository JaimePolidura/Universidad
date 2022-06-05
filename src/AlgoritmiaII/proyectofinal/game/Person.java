package AlgoritmiaII.proyectofinal.game;

public final class Person{
    private final PersonState state;
    private final int daysSinceInfection;

    public Person(PersonState state, int daysSinceInfection) {
        this.state = state;
        this.daysSinceInfection = daysSinceInfection;
    }

    public boolean isInfected(){
        return this.state == PersonState.INFECTED;
    }

    public Person masked(){
            return new Person(PersonState.MASKED, daysSinceInfection);
    }

    public boolean isNotInfected(){
        return !this.isInfected();
    }

    public int getDaysSinceInfection() {
        return daysSinceInfection;
    }

    public PersonState getState() {
        return this.state;
    }

    public Person infect(){
        return this.incrementDaysSinceInfection()
                .withState(PersonState.INFECTED);
    }

    public Person withState(PersonState state){
        return new Person(state, daysSinceInfection);
    }

    public boolean isDeath(){
        return this.state == PersonState.DEATH;
    }

    public boolean isMasked(){
        return this.state == PersonState.MASKED;
    }

    public Person incrementDaysSinceInfection(){
        return new Person(state,daysSinceInfection + 1);
    }

    public static Person infected(){
        return new Person(PersonState.INFECTED, -1);
    }

    public static Person notInfected(){
        return new Person(PersonState.NOT_INFECTED, -1);
    }
}
