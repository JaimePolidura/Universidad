package AlgoritmiaI.entregas.ejercicio4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public final class Airport implements TowerControlSubscriber{
    private final Queue<Plane> departures;
    private final Queue<Plane> pendingLandingsStrip;
    private final Plane[] strips;

    public Airport () {
        this.departures = new LinkedList<>();
        this.pendingLandingsStrip = new LinkedList<>();
        this.strips = new Plane[] { null, null };
    }

    public void addPlane(Plane plane){
        pendingLandingsStrip.add(plane);

        onNewPlaneInQueue(plane);
    }

    private void onNewPlaneInQueue(Plane plane) {
        if(availableStrip()){
            addPlaneToStrip();
        }
    }

    @Override
    public void landingStrip1IsReady() {
        takeOffPlane(0);
    }

    @Override
    public void landingStrip2IsReady() {
        takeOffPlane(1);
    }

    private boolean availableStrip (){
        return Arrays.stream(strips).anyMatch(Objects::isNull);
    }

    private void takeOffPlane(int strip) {
        departures.add(strips[strip]);
        strips[strip] = null;
        addPlaneToStrip();
    }

    private void addPlaneToStrip () {
        for(int i = 0; i < strips.length; i++){
            if(strips[i] == null){
                strips[i] = pendingLandingsStrip.poll();
                return;
            }
        }
    }

    public Queue<Plane> getWaitingForLandingStrip() {
        return pendingLandingsStrip;
    }

    public Plane getLandingStrip1Plane() {
        return strips[0];
    }

    public Plane getLandingStrip2Plane() {
        return strips[1];
    }

    public Queue<Plane> getDepartures() {
        return departures;
    }
}
