package multithreadingbankapplicationwithsocketprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DoğukanBuğra
 */
public class Bank {
    
    private int numberOfCustomers;
    private double balance;
    public List<Employee> Employees;  
    public List<Customer> Customers;
    
    public Bank(){
        if(!MultiThreadingBankApplicationWithSocketProgramming.isBankCreated){
            this.numberOfCustomers = 0;
            this.balance = 1000;
            this.Employees = new ArrayList<>();  
            this.Customers = new ArrayList<>();
            MultiThreadingBankApplicationWithSocketProgramming.isBankCreated = true;
        }
    }
    
    public void increaseNumberOfCustomers(){
        this.numberOfCustomers++;
    }
    
    
    public synchronized void addMoney(int money){
        this.balance += money;
        System.out.println("Successfully add operation on Bank");
        System.out.println("Now Bank has " + this.getBankBalance() + "$");
    }
    
    public synchronized void reduceMoney(int money){
        if(this.balance >= money){
            this.balance -= money;
            System.out.println("Successful reduce operation on Bank");
            System.out.println("Now Bank has " + this.getBankBalance() + "$");
            
        }
        else{
            System.out.println("Bank doesn't have that amount");
            
        }
    }
    
    public double getBankBalance(){
        return this.balance;
    }
}
