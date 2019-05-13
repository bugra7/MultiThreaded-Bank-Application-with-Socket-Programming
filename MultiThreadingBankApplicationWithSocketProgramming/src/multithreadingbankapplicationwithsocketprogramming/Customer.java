package multithreadingbankapplicationwithsocketprogramming;
/**
 *
 * @author DoğukanBuğra
 */
public class Customer {
    
    String name;
    String surname;
    private String password;
    private int id;
    private BankAccount bankAccount;    //Assumption: One person can only have 1 Bank Account
    private double[] transactionList;

    public Customer(String name, String surname, String password, int id, BankAccount bankAccount, double[] transactionList) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.id = id;
        this.bankAccount = bankAccount;
        this.transactionList = transactionList;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public int getId() {
        return id;
    }

    public synchronized BankAccount getBankAccount() {
        return bankAccount;
    }

    public double[] getTransactionList() {
        return transactionList;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setTransactionList(double[] transactionList) {
        this.transactionList = transactionList;
    }
    
}