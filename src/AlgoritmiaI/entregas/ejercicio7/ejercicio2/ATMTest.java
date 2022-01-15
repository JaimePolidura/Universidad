package AlgoritmiaI.entregas.ejercicio7.ejercicio2;


import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ATMTest {
    private ATM atm;

    @Before
    public void setUp() throws Exception {
        new File(ATM.getFilename()).delete();
        atm = new ATM();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, atm.getNumBillsOf10());
        assertEquals(0, atm.getNumBillsOf20());
        assertEquals(0, atm.getNumBillsOf50());
    }

    @Test
    public void testDeposit() throws Exception {
        atm.deposit(3, 1, 1);
        assertEquals(3, atm.getNumBillsOf10());
        assertEquals(1, atm.getNumBillsOf20());
        assertEquals(1, atm.getNumBillsOf50());
    }

    @Test
    public void testDepositTwice() throws Exception {
        atm.deposit(3, 1, 1);
        atm.deposit(2, 3, 7);
        assertEquals(5, atm.getNumBillsOf10());
        assertEquals(4, atm.getNumBillsOf20());
        assertEquals(8, atm.getNumBillsOf50());
    }

    @Test
    public void testDepositAndRecreate() throws Exception {
        atm.deposit(3, 1, 1);

        assertEquals(3, atm.getNumBillsOf10());
        assertEquals(1, atm.getNumBillsOf20());
        assertEquals(1, atm.getNumBillsOf50());
    }

    @Test
    public void testWithdraw10() throws Exception {
        atm.deposit(2, 2, 2);
        atm.withdraw(10);

        assertEquals(1, atm.getNumBillsOf10());
        assertEquals(2, atm.getNumBillsOf20());
        assertEquals(2, atm.getNumBillsOf50());
    }

    @Test
    public void testWithdraw30() throws Exception {
        atm.deposit(2, 2, 2);
        atm.withdraw(30);

        assertEquals(1, atm.getNumBillsOf10());
        assertEquals(1, atm.getNumBillsOf20());
        assertEquals(2, atm.getNumBillsOf50());
    }

    @Test
    public void testWithdraw60() throws Exception {
        atm.deposit(2, 2, 2);
        atm.withdraw(60);

        assertEquals(1, atm.getNumBillsOf10());
        assertEquals(2, atm.getNumBillsOf20());
        assertEquals(1, atm.getNumBillsOf50());
    }

    @Test
    public void testWithdrawNotEnoughFunds() throws Exception {
        atm.deposit(2, 2, 2);

        assertThrows(RuntimeException.class, () -> atm.withdraw(200));
    }

    @Test
    public void testWithdrawEmpty() {
        assertThrows(RuntimeException.class, () -> atm.withdraw(10));
    }

    @Test
    public void testWithdrawInvalidAmount() throws Exception {
        atm.deposit(2, 2, 2);

        assertThrows(RuntimeException.class, () -> atm.withdraw(55));
    }
    @Test
    public void testWithdrawTwice() throws Exception {
        atm.deposit(2, 2, 2);
        atm.withdraw(30);
        atm.withdraw(60);

        assertEquals(0, atm.getNumBillsOf10());
        assertEquals(1, atm.getNumBillsOf20());
        assertEquals(1, atm.getNumBillsOf50());
    }

    @Test
    public void testWithdrawAndRecreate() throws Exception {
        atm.deposit(2, 2, 2);
        atm.withdraw(30);

        assertEquals(1, atm.getNumBillsOf10());
        assertEquals(1, atm.getNumBillsOf20());
        assertEquals(2, atm.getNumBillsOf50());
    }
}
