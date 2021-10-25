package AlgoritmiaI.clase.practica3;

import AlgoritmiaI.datastructures.linkedlist.MyLinkedList;

import java.util.LinkedList;
import java.util.Queue;

public final class SingleRowManager {
    private int rowThreshold;
    private final Queue<Integer> row;
    private final MyLinkedList<CheckoutPoint> checkoutPoints;
    private RowManagerEventListerner eventListener;

    public SingleRowManager() {
        //Default value
        this.rowThreshold = 10;

        this.row = new LinkedList<>();
        this.checkoutPoints = new MyLinkedList<>();
        this.checkoutPoints.insert(new CheckoutPoint());
    }

    public MyLinkedList<CheckoutPoint> getCheckoutPoints() {
        return checkoutPoints;
    }

    public void setRowThreshold(int rowThreshold) {
        this.rowThreshold = rowThreshold;
    }

    public void addClient (int client) {
        if(row.size() + 1 >= rowThreshold){
            eventListener.needMoreCheckoutPoints();
        }

        this.row.add(client);

        CheckoutPoint availableCheckPoint = getAvailableCheckPoint();

        if(availableCheckPoint != null) {
            availableCheckPoint.setCurrentClient(this.row.poll());
        }
    }

    private CheckoutPoint getAvailableCheckPoint () {
        CheckoutPoint checkoutPointToReturn = null;

        for(CheckoutPoint checkoutPoint : checkoutPoints){
            if(checkoutPoint.isFree()){
                checkoutPointToReturn = checkoutPoint;
                break;
            }
        }

        return null;
    }

    public void addCheckoutPoint (){
        this.checkoutPoints.insert(new CheckoutPoint());
    }

    public void checkoutCompleted(CheckoutPoint checkoutPoint) {
        checkoutPoint.complete();

        if(this.row.size() > 0){
            checkoutPoint.setCurrentClient(this.row.poll());
        }
    }

    public void setEventListener(RowManagerEventListerner eventListener) {
        this.eventListener = eventListener;
    }

    public Queue<Integer> getRow () {
        return this.row;
    }
}
