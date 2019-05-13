package multithreadingbankapplicationwithsocketprogramming;

import java.util.ArrayList;
/**
 *
 * @author DoğukanBuğra
 */
public class BankAccount {
    
    int accountNo;
    double accountBalance;
    String text;
    ArrayList<Integer> transactionList;

    public BankAccount(int accountNo, double accountBalance, String text) {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
        this.text = text;
        this.transactionList =  new ArrayList<Integer>();
    }
    
    public synchronized int getAccountNo() {
        return accountNo;
    }

    public synchronized double getAccountBalance() {
        return accountBalance;
    }

    public synchronized String getText() {
        return text;
    }

    public synchronized void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public synchronized void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public synchronized void setText(String text) {
        this.text = text;
    }    
    
    public synchronized void payIn(int money){
        this.accountBalance += money;
        this.transactionList.add(money);
        System.out.println("Successful pay in");
        System.out.println("Now Account Number " + this.getAccountNo() +  " has " + this.getAccountBalance() + "$ in the account");
    }
    
    public synchronized boolean withdraw(int money){
        if(this.accountBalance >= money){
            this.accountBalance -= money;
            this.transactionList.add(-1*money);
            System.out.println("Successful withdraw");
            System.out.println("Now Account Number " + this.getAccountNo() +  " has " + this.getAccountBalance() + "$ in the account");
            return true;
        }
        else{
            System.out.println("Account Number " + this.getAccountNo() + " doesn't have sufficient amount in the account!");
            return false;
        }
    }
}