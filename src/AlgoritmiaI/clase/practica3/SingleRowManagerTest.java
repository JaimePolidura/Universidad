package AlgoritmiaI.clase.practica3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleRowManagerTest {
    private static final Random random = new Random(LocalTime.now().getNano());
    private SingleRowManager rowManager;

    @BeforeEach
    public void setup(){
        this.rowManager = new SingleRowManager();
    }

    @Test
    public void freshSingleRowManager(){
        assertEquals(1, rowManager.getCheckoutPoints().size());
        assertTrue(rowManager.getRow().isEmpty());
    }

    @Test
    public void addOneClient(){
        Integer clientId = createIdClient();
        rowManager.addClient(clientId);

        CheckoutPoint checkout = rowManager.getCheckoutPoints().get(0);

        assertEquals(clientId, checkout.getCurrentClient());
        assertTrue(rowManager.getRow().isEmpty());
    }

    @Test
    public void addTwoClients(){
        int[] clients = new int[]{ createIdClient(), createIdClient() };

        int expectedClientOnCheckout = clients[0];
        int expectedClientOnRow = clients[1];

        rowManager.addClient(expectedClientOnCheckout);
        rowManager.addClient(expectedClientOnRow);

        CheckoutPoint checkoutPoint = rowManager.getCheckoutPoints().get(0);

        assertEquals(expectedClientOnCheckout, checkoutPoint.getCurrentClient());
        assertArrayEquals(new Integer[]{ expectedClientOnRow }, rowManager.getRow().toArray());
    }

    @Test
    public void testNeedMoreCheckoutNotification(){
        AtomicBoolean eventRaised = new AtomicBoolean(false);

        rowManager.setRowThreshold(4);
        rowManager.setEventListener(() -> eventRaised.set(true));

        // Enters 5 new clients
        Integer[] clients = new Integer[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        assertTrue(eventRaised.get());
    }

    @Test
    public void addMoreCheckoutPointsToDecreaseRow(){
        Integer[] clients = new Integer[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        rowManager.setRowThreshold(clients.length-1);
        rowManager.setEventListener(() -> rowManager.addCheckoutPoint());

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        CheckoutPoint secondCheckoutPoint = rowManager.getCheckoutPoints().get(1);

        Integer expectedSecondClientOrCheckout = clients[1];

        assertEquals(expectedSecondClientOrCheckout, secondCheckoutPoint.getCurrentClient());
        assertEquals(3, rowManager.getRow().size());

    }

    @Test
    public void checkoutProcessCompletedAndSetNext(){
        int[] clients = new int[]{
                createIdClient(),
                createIdClient(),
                createIdClient(),
        };

        for(Integer client: clients){
            rowManager.addClient(client);
        }

        CheckoutPoint checkoutPoint = rowManager.getCheckoutPoints().get(0);

        rowManager.checkoutCompleted(checkoutPoint);

        int expectedNextClient = clients[1];
        int expectedClientOnRow = clients[2];

        assertEquals(expectedNextClient, checkoutPoint.getCurrentClient());
        assertArrayEquals(new Integer[]{ expectedClientOnRow }, rowManager.getRow().toArray());
    }

    private Integer createIdClient() {
        return random.nextInt();
    }
}
