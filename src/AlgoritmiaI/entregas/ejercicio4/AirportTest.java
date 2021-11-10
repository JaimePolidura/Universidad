package AlgoritmiaI.entregas.ejercicio4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public final class AirportTest {
    private Plane[] planes;
    private Airport airport;
    private TowerControl towerControl;

    @BeforeEach
    void setup() {
        final Plane madridPlane = new Plane(1001, "Ryanair", "Madrid");
        final Plane sanFranciscoPlane = new Plane(1002, "US Airways", "San Francisco");
        final Plane menorcaPlane = new Plane(1003, "Iberia", "Iberia");
        final Plane birminghamPlane = new Plane(1004, "Vueling", "Birmingham");
        final Plane berlinPlane = new Plane(1005, "EasyJet", "Barcelona");
        final Plane barcelonaPlane = new Plane(1006, "Ryanair", "Barcelona");
        final Plane parisPlane = new Plane(1007, "Iberia", "Paris");
        final Plane newYorkPlane = new Plane(1008, "US Airways", "New York");
        final Plane philadelphiaPlane = new Plane(1009, "US Airways", "Philadelphia");
        final Plane irvinePlane = new Plane(1010, "Air France", "Irvine");

        planes = new Plane[] {
                madridPlane,
                sanFranciscoPlane,
                menorcaPlane,
                birminghamPlane,
                berlinPlane,
                barcelonaPlane,
                parisPlane,
                newYorkPlane,
                philadelphiaPlane,
                irvinePlane,
        };

        airport = new Airport();
        towerControl = new TowerControl();
        towerControl.subscribe(airport);
    }

    @Test
    public void testAirportInitialization(){
        assertArrayEquals(new Plane[0], airport.getWaitingForLandingStrip().toArray());
        assertNull(airport.getLandingStrip1Plane());
        assertNull(airport.getLandingStrip2Plane());
    }

    @Test
    public void setFirstPlanesInLandingStrip(){
        fillWithPlanes(airport);
        Plane expectedFirstPlane = planes[0];
        Plane expectedSecondPlane = planes[1];

        assertEquals(expectedFirstPlane, airport.getLandingStrip1Plane());
        assertEquals(expectedSecondPlane, airport.getLandingStrip2Plane());
    }

    @Test
    public void waitingPlanesForLandingStrip(){
        fillWithPlanes(airport);
        Plane[] expectedPlanesWaiting = Arrays.copyOfRange(planes, 2, planes.length);

        assertArrayEquals(expectedPlanesWaiting, airport.getWaitingForLandingStrip().toArray());
    }

    @Test
    public void takeoffOnePlaneFromFirstLandingStrip(){
        fillWithPlanes(airport);
        towerControl.notifyLandingStrip1IsReady();
        Plane expectedPlaneOnAir = planes[0];
        Plane expectedPlaneInLandingStrip2 = planes[1];
        Plane expectedPlaneInLandingStrip1 = planes[2];
        Plane[] expectedPlanesWaiting = Arrays.copyOfRange(planes, 3, planes.length);

        assertEquals(expectedPlaneOnAir, airport.getDepartures().poll());
        assertEquals(expectedPlaneInLandingStrip1, airport.getLandingStrip1Plane());
        assertEquals(expectedPlaneInLandingStrip2, airport.getLandingStrip2Plane());
        assertArrayEquals(expectedPlanesWaiting, airport.getWaitingForLandingStrip().toArray());
    }

    @Test
    public void takeoffOnePlaneFromSecondLandingStrip(){
        fillWithPlanes(airport);
        towerControl.notifyLandingStrip2IsReady();
        Plane expectedPlaneOnAir = planes[1];
        Plane expectedPlaneInLandingStrip1 = planes[0];
        Plane expectedPlaneInLandingStrip2 = planes[2];
        Plane[] expectedPlanesWaiting = Arrays.copyOfRange(planes, 3, planes.length);

        assertEquals(expectedPlaneOnAir, airport.getDepartures().poll());
        assertEquals(expectedPlaneInLandingStrip1, airport.getLandingStrip1Plane());
        assertEquals(expectedPlaneInLandingStrip2, airport.getLandingStrip2Plane());
        assertArrayEquals(expectedPlanesWaiting,airport.getWaitingForLandingStrip().toArray());
    }

    @Test
    public void takeoffTwoPlanes(){
        fillWithPlanes(airport);
        Plane expectedFirstPlane = airport.getLandingStrip1Plane();
        Plane expectedSecondPlane = airport.getLandingStrip2Plane();
        towerControl.notifyLandingStrip1IsReady();
        towerControl.notifyLandingStrip2IsReady();
        Plane[] expectedPlanesWaiting = Arrays.copyOfRange(planes, 4, planes.length);

        assertEquals(expectedFirstPlane, airport.getDepartures().poll());
        assertEquals(expectedSecondPlane, airport.getDepartures().poll());
        assertArrayEquals(expectedPlanesWaiting, airport.getWaitingForLandingStrip().toArray());
    }

    @Test
    public void takeoffAllPlanes(){
        fillWithPlanes(airport);

        for(int i = 0; i < planes.length/2; ++i){
            towerControl.notifyLandingStrip1IsReady();
            towerControl.notifyLandingStrip2IsReady();
        }

        assertTrue(airport.getWaitingForLandingStrip().isEmpty());
        assertArrayEquals(planes, airport.getDepartures().toArray());
        assertNull(airport.getLandingStrip1Plane());
        assertNull(airport.getLandingStrip2Plane());
    }

    private void fillWithPlanes(Airport airport){
        Arrays.stream(planes).forEach(airport::addPlane);
    }
}
