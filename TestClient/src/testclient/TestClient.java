package testclient;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;


public class TestClient {
    
    private static Scanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws Exception {
        
        new Thread(new Sender()).start();
    }

    public static class SimpleCommand implements java.io.Serializable {

        String test;

        public SimpleCommand(int i) {
            this.test = Integer.toString(i); 
        }

            public String toString() {
            return this.test;
        }
    }
    
    private static class Sender implements Runnable {

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", 987);
                                
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
                
                Scanner input = new Scanner(System.in);
                System.out.println("Press 1 to log in as a Customer");
                System.out.println("Press 2 to log in as an Employee");
                System.out.println("Press any other key to exit");
                int money = 0, selection2, selection = input.nextInt();
                
                 while(selection!=3){
                    if(selection==1){
                        System.out.println("Enter your ID");
                        int id = input.nextInt();
                        System.out.println("Enter your password");
                        String password = input.next();
                        
                        out.println("CustomerID : " + id);
                        out.println("CustomerPassword : " + password);
                        
                        String answer = in.nextLine();
                        System.out.println(answer);
                        if(answer.startsWith("Successful Login")){
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            selection2 = input.nextInt();
                            
                            if(selection2==1){
                                System.out.println("Enter amount of money you want to pay in : ");
                                money = input.nextInt();
                                out.println("Selection2 : " + selection2);
                                out.println("Money1 : " + money);
                                
                                System.out.println(in.nextLine());
                                System.out.println(in.nextLine());
                            }
                            else if(selection2==2){
                                System.out.println("Enter amount of money you want to withdraw : ");
                                money = input.nextInt();
                                out.println("Selection2 : " + selection2);
                                out.println("Money1 : " + money);
                                
                                System.out.println(in.nextLine());
                            }
                            else{
                                System.out.println("Wrong selection!");
                            }
                        }
                        else{
                            System.out.println("Login failed!");
                        }
                    }
                    
                    else if(selection==2){
                        System.out.println("Enter your ID");
                        int EmployeeID = input.nextInt();
                        System.out.println("Enter your password");
                        String EmployeePassword = input.next();
                        
                        out.println("EmployeeID : " + EmployeeID);
                        out.println("EmployeePassword : " + EmployeePassword);
                        
                        String answer = in.nextLine();
                        System.out.println(answer);
                        if(answer.startsWith("Successful Login")){
                            String s = in.nextLine();
                            System.out.println(s);
                            s = in.nextLine();
                            System.out.println(s);
                            s = in.nextLine();
                            System.out.println(s);
                            selection2 = input.nextInt();
                        
                            //Create a customer
                            if(selection2 == 1){
                                System.out.println("Enter customer ID");
                                int createCustomerID = input.nextInt();
                                System.out.println("Enter customer password");
                                String createCustomerPassword = input.next();
                                System.out.println("Enter customer name");
                                String name = input.next();
                                System.out.println("Enter customer surname");
                                String surname = input.next();
                                System.out.println("Enter customer bank account no");
                                int accountNo = input.nextInt();
                                
                                out.println("createCustomerID : " + createCustomerID);
                                out.println("createCustomerPassword : " + createCustomerPassword);
                                out.println("name : " + name);
                                out.println("surname : " + surname);
                                out.println("accountNo : " + accountNo);
                                
                                System.out.println(in.nextLine());
                            }

                            //Create an employee
                            else if(selection2 == 2){
                                System.out.println("Enter employee ID");
                                int createEmployeeID = input.nextInt();
                                System.out.println("Enter employee password");
                                String createEmployeePassword = input.next();
                                System.out.println("Enter employee name");
                                String name = input.next();
                                System.out.println("Enter employee surname");
                                String surname = input.next();
                                System.out.println("Enter employee authority degree");
                                int authoritydegree = input.nextInt();
                                
                                out.println("createEmployeeID : " + createEmployeeID);
                                out.println("createEmployeePassword : " + createEmployeePassword);
                                out.println("name : " + name);
                                out.println("surname : " + surname);
                                out.println("authoritydegree : " + authoritydegree);
                                
                                System.out.println(in.nextLine());
                            }
                        }
                        else{
                            System.out.println("Login failed!");
                        }
                    }
                    else{
                        out.println("Close Socket");
                        break;
                    }    
                    
                    System.out.println("Press 1 to log in as a Customer");
                    System.out.println("Press 2 to log in as an Employee");
                    System.out.println("Press any other key to exit");
                    selection = input.nextInt();
                }
                
                
                
            } catch(Exception e) {
                
                System.out.println("Error sending data. " + e);
                
            }
        }
        
    }
}










/*
package testclient;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;


public class TestClient {

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 20; i++)
            new Thread(new Sender()).start();
    }

    public static class SimpleCommand implements java.io.Serializable {

        String test;

        public SimpleCommand(int i) {
            this.test = Integer.toString(i); 
        }

            public String toString() {
            return this.test;
        }


    }
    
    private static class Sender implements Runnable {

        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", 99);
                ObjectOutputStream oStream = new ObjectOutputStream(socket.getOutputStream());
                for(int i = 0; i < 10; i++) {
                    oStream.writeObject(new Date());
                }
                socket.close();
            } catch(Exception e) {
                System.out.println("Error sending data. " + e);
                return;
            }
        }
    }

}
*/