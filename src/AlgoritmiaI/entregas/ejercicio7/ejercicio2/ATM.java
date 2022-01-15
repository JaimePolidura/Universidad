package AlgoritmiaI.entregas.ejercicio7.ejercicio2;

import java.io.*;

public class ATM {
    private int numberOfBillsOf10;
    private int numberOfBillsOf20;
    private int numberOfBillsOf50;

    public static String getFilename() {
        return "src/AlgoritmiaI/entregas/ejercicio7/ejercicio2/bd.txt";
    }

    public ATM() throws IOException {
        setUp();
    }

    private void setUp() throws IOException {
        new File(getFilename()).createNewFile();

        try(BufferedReader reader = new BufferedReader(new FileReader(getFilename()))){
            try{
                this.numberOfBillsOf10 = Integer.parseInt(reader.readLine());
                this.numberOfBillsOf20 = Integer.parseInt(reader.readLine());
                this.numberOfBillsOf50 = Integer.parseInt(reader.readLine());
            }catch (Exception e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }

    public void deposit(int numberOfBills10, int numberOfBills20, int numberOfBills50) throws Exception {
        this.numberOfBillsOf10 += numberOfBills10;
        this.numberOfBillsOf20 += numberOfBills20;
        this.numberOfBillsOf50 += numberOfBills50;

        onBillsNumberChanged();
    }

    public void withdraw(int toWithdraw) throws Exception {
        if(!canWithdraw(toWithdraw)){
            throw new RuntimeException("Cannot withdraw");
        }

        int numberOfbillsO50ToWithdraw = 0;
        int numberOfbillsO20ToWithdraw = 0;
        int numberOfbillsO10ToWithdraw = 0;

        int remaining = toWithdraw;
        if(remaining >= 50){
            int totalBills = remaining / 50;

            if(totalBills > this.numberOfBillsOf50){
                totalBills = this.numberOfBillsOf50;
            }

            remaining = remaining - (totalBills * 50);
            numberOfbillsO50ToWithdraw = totalBills;
        }
        if(remaining >= 20 && remaining >= 0){
            int totalBills = remaining / 20;

            if(totalBills > this.numberOfBillsOf20){
                totalBills = this.numberOfBillsOf20;
            }

            remaining = remaining - (totalBills * 20);
            numberOfbillsO20ToWithdraw = totalBills;
        }
        if(remaining >= 10 && remaining >= 0){
            int totalBills = remaining / 10;

            if(totalBills > this.numberOfBillsOf10){
                totalBills = this.numberOfBillsOf10;
            }

            remaining = remaining - (totalBills * 10);
            numberOfbillsO10ToWithdraw = totalBills;
        }

        this.numberOfBillsOf50 = numberOfbillsO50ToWithdraw >= numberOfBillsOf50 ? 0 : this.numberOfBillsOf50 - numberOfbillsO50ToWithdraw;
        this.numberOfBillsOf20 = numberOfbillsO20ToWithdraw >= numberOfBillsOf20 ? 0 : this.numberOfBillsOf20 - numberOfbillsO20ToWithdraw;;
        this.numberOfBillsOf10 = numberOfbillsO10ToWithdraw >= numberOfBillsOf10 ? 0 : this.numberOfBillsOf10 - numberOfbillsO10ToWithdraw;;

        onBillsNumberChanged();
    }

    private boolean canWithdraw(int toWithdraw){
        int remaining = toWithdraw;

        remaining = withdrawInBillsOf(numberOfBillsOf50,50, remaining);
        remaining = withdrawInBillsOf(numberOfBillsOf20,20, remaining);
        remaining = withdrawInBillsOf(numberOfBillsOf10,10, remaining);

        return remaining == 0;
    }

    private int withdrawInBillsOf(int numberOfBillNumbers, int billNumber, int toWithdraw) {
        if(toWithdraw >= billNumber && toWithdraw > 0){
            int billsWithdrawn = getNumberOfBills(toWithdraw, billNumber);

            if(billsWithdrawn > numberOfBillNumbers){
                billsWithdrawn = numberOfBillNumbers;
            }

            toWithdraw = toWithdraw - (billsWithdrawn * billNumber);
        }

        return toWithdraw;
    }

    private int getNumberOfBills(int money, int billNumber){
        if(money <= 0) return 0;

        return money / billNumber;
    }

    public int getNumBillsOf10() {
        return numberOfBillsOf10;
    }

    public int getNumBillsOf20() {
        return numberOfBillsOf20;
    }

    public int getNumBillsOf50() {
        return numberOfBillsOf50;
    }

    private void onBillsNumberChanged() throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFilename()))){
            writer.write(this.numberOfBillsOf10 + "\n");
            writer.write(this.numberOfBillsOf20 + "\n");
            writer.write(this.numberOfBillsOf50 + "\n");
        }
    }
}
