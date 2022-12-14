package es.jaime.entregas.ejercicio4;

public final class TowerControl {
    private TowerControlSubscriber subscriber;

    public void subscribe (TowerControlSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void notifyLandingStrip1IsReady() {
        subscriber.landingStrip1IsReady();
    }

    public void notifyLandingStrip2IsReady() {
        subscriber.landingStrip2IsReady();
    }
}
