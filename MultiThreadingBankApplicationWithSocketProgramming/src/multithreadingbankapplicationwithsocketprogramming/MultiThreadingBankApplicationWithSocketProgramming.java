package multithreadingbankapplicationwithsocketprogramming;
/**
 *
 * @author DoğukanBuğra
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiThreadingBankApplicationWithSocketProgramming {

    private ServerSocket serverSock;
    public static boolean isBankCreated = false;
    
    //Create Bank profile
    public static Bank bank = new Bank();
    Employee employee;
    Customer customer;
    int id, accountNo, authoritydegree, selection, selection2, money;
    String name, surname, password, password2;

    public static void main(String[] args) throws Exception {
                 
        //Create Admin profile
        Employee Admin = new Employee("Bugra", "Anadol", "123", 0, 5);
        bank.Employees.add(Admin);   
        
        MultiThreadingBankApplicationWithSocketProgramming server = new MultiThreadingBankApplicationWithSocketProgramming();
        server.start();
    }

    private void start() throws Exception {
        LinkedBlockingQueue<Socket> queue = new LinkedBlockingQueue<>();
        serverSock = new ServerSocket(987);
        new Thread(new Worker(queue), "Worker1").start();
        new Thread(new Worker(queue), "Worker2").start();

        while (true) {
            queue.put(serverSock.accept());
        }
    }

    private class Worker implements Runnable {
        private LinkedBlockingQueue<Socket> socketQueue;
        
        Scanner input;
        PrintWriter output;

        public Worker(LinkedBlockingQueue<Socket> queue) {
            this.socketQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket clientSocket = socketQueue.take();
                    input = new Scanner(clientSocket.getInputStream());
                    output = new PrintWriter(clientSocket.getOutputStream(), true);
               
                    while (true) {
                        try {
                            String inputFromClient = input.nextLine();
                            int id = -999;
                            String password = "-";

                            if(inputFromClient.startsWith("CustomerID : ")){
                                inputFromClient = inputFromClient.replace("CustomerID : ","");
                                id = Integer.parseInt(inputFromClient);
                                
                                inputFromClient = input.nextLine();
                                if(inputFromClient.startsWith("CustomerPassword : ")){
                                    inputFromClient = inputFromClient.replace("CustomerPassword : ","");
                                    password = password.replace("-", inputFromClient);
                                }
                                
                                customer = findCustomerById(id);
                                if(customer==null){
                                    output.println("Login failed!");
                                }
                                else if(customer.getPassword().equals(password)){
                                    output.println("Successful Login"); 
                                    output.println("Welcome " + customer.getName() + " " + customer.getSurname());
                                    output.println("Amount of money you have in your account is : " + customer.getBankAccount().getAccountBalance() + "$");
                                    output.println("Press 1 to pay in money to your account");
                                    output.println("Press 2 to withdraw money from your account");
                                    
                                    inputFromClient = input.nextLine();
                                    if(inputFromClient.startsWith("Selection2 : ")){
                                        inputFromClient = inputFromClient.replace("Selection2 : ","");
                                        selection2 = Integer.parseInt(inputFromClient);
                                    }
                                    inputFromClient = input.nextLine();
                                    
                                    if(inputFromClient.startsWith("Money1 : ")){
                                        inputFromClient = inputFromClient.replace("Money1 : ","");
                                        money = Integer.parseInt(inputFromClient);
                                    }
                                    
                                    if(selection2==1){
                                        customer.getBankAccount().payIn(money);
                                        bank.addMoney(money);
                                        output.println("Successful pay in");
                                        output.println("Now you have " + customer.getBankAccount().getAccountBalance() + "$ in your account");
                                    }
                                    else if(selection2==2){
                                        if(customer.getBankAccount().withdraw(money)){
                                            bank.reduceMoney(money); 
                                            output.println("Successful withdraw. " + "Now you have " + customer.getBankAccount().getAccountBalance() + "$ in your account");
                                        }
                                        else{
                                            output.println("You don't have that amount in your account!");
                                        }
                                    }
                                }
                                else output.println("Login failed!");
                            }
                            if(inputFromClient.startsWith("EmployeeID : ")){
                                inputFromClient = inputFromClient.replace("EmployeeID : ","");
                                id = Integer.parseInt(inputFromClient);
                                
                                inputFromClient = input.nextLine();
                                if(inputFromClient.startsWith("EmployeePassword : ")){
                                    inputFromClient = inputFromClient.replace("EmployeePassword : ","");
                                    password = password.replace("-", inputFromClient);
                                }
                                
                                employee = findEmployeeById(id);
                                if(employee==null){
                                    output.println("Login failed!");
                                }
                                else if(employee.getPassword().equals(password)){
                                    output.println("Successful Login"); 
                                    output.println("Welcome " + employee.getName() + " " + employee.getSurname());
                                    output.println("Press 1 to create a Customer");
                                    output.println("Press 2 to create an Employee");
                                    
                                    inputFromClient = input.nextLine();
                                    
                                    if(inputFromClient.startsWith("createCustomerID : ")){
                                        
                                        inputFromClient = inputFromClient.replace("createCustomerID : ","");
                                        id = Integer.parseInt(inputFromClient);

                                        inputFromClient = input.nextLine();
                                        
                                        if(inputFromClient.startsWith("createCustomerPassword : ")){

                                            password = inputFromClient.replace("createCustomerPassword : ","");

                                            inputFromClient = input.nextLine();
                                            if(inputFromClient.startsWith("name : ")){
                                                name = inputFromClient.replace("name : ","");

                                                inputFromClient = input.nextLine();
                                                if(inputFromClient.startsWith("surname : ")){
                                                    surname = inputFromClient.replace("surname : ","");

                                                    inputFromClient = input.nextLine();
                                                    if(inputFromClient.startsWith("accountNo : ")){
                                                        inputFromClient = inputFromClient.replace("accountNo : ","");
                                                        accountNo = Integer.parseInt(inputFromClient);
                                                        
                                                        BankAccount bankAccount = new BankAccount(accountNo, 0, "");
                                                        customer = new Customer(name, surname, password, id, bankAccount, null);
                                                        bank.increaseNumberOfCustomers();
                                                        bank.Customers.add(customer);
                                                        output.println("Customer created successfully");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if(inputFromClient.startsWith("createEmployeeID : ")){
                                        inputFromClient = inputFromClient.replace("createEmployeeID : ","");
                                        id = Integer.parseInt(inputFromClient);

                                        inputFromClient = input.nextLine();
                                        
                                        if(inputFromClient.startsWith("createEmployeePassword : ")){
                                            password = inputFromClient.replace("createEmployeePassword : ","");

                                            inputFromClient = input.nextLine();
                                            if(inputFromClient.startsWith("name : ")){
                                                name = inputFromClient.replace("name : ","");

                                                inputFromClient = input.nextLine();
                                                if(inputFromClient.startsWith("surname : ")){
                                                    surname = inputFromClient.replace("surname : ","");

                                                    inputFromClient = input.nextLine();
                                                    if(inputFromClient.startsWith("authoritydegree : ")){
                                                        inputFromClient = inputFromClient.replace("authoritydegree : ","");
                                                        authoritydegree = Integer.parseInt(inputFromClient);
                                                        
                                                        employee = new Employee(name, surname, password, id, authoritydegree);
                                                        bank.Employees.add(employee);
                                                        output.println("Employee created successfully");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                else output.println("Login failed!");
                            } 
                            
                            if(inputFromClient.startsWith("Close Socket")){
                                clientSocket.close();
                            }             
                    }   catch (IOException ex) {
                            Logger.getLogger(MultiThreadingBankApplicationWithSocketProgramming.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }   catch (IOException ex) {
                    Logger.getLogger(MultiThreadingBankApplicationWithSocketProgramming.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiThreadingBankApplicationWithSocketProgramming.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
}   
    
        public static Customer findCustomerById(int id){
        for (Customer customer : bank.Customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    } 
    
    public static Employee findEmployeeById(int id){
        for (Employee employee : bank.Employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    } 
}