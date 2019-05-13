
/**
 *
 * @author DoğukanBuğra
 */
public class SimpleCommand implements java.io.Serializable {
 
    String test;
    
    public SimpleCommand(int i) {
        this.test = Integer.toString(i); 
    }
    
        public String toString() {
        return this.test;
    }
    
        
}
