package AlgoritmiaI.clase.practica3;

public class CheckoutPoint {
    private int currentClient = -1;
    private boolean isFree = true;

    public int getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(int currentClient) {
        this.currentClient = currentClient;
        isFree = false;
    }

    public boolean isFree(){
        return isFree;
    }

    public void complete(){
        isFree = true;
    }
}
