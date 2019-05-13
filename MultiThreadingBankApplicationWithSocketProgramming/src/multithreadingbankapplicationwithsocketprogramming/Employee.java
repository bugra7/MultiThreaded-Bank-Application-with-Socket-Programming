package multithreadingbankapplicationwithsocketprogramming;
/**
 *
 * @author DoğukanBuğra
 */
public class Employee {
    
    String name;
    String surname;
    private String password;
    private int id;
    int authorityDegree;

    public Employee(String name, String surname, String password, int id, int authorityDegree) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.id = id;
        this.authorityDegree = authorityDegree;
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

    public int getAuthorityDegree() {
        return authorityDegree;
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

    public void setAuthorityDegree(int authorityDegree) {
        this.authorityDegree = authorityDegree;
    }
}