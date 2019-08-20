/**
 *
 * @author Abdelrahman Badran
 */
package busterminal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class BusTerminal {
    
            
    public static void main(String[] args) throws InterruptedException {
        int custID = 1;
                
        WaitingArea2 WA2 = new WaitingArea2();
        WA2.startBus2();
        
        ExecutorService CustomerExec = null;
              
        try{
           CustomerExec = Executors.newFixedThreadPool(100);
     
        for (int i = 0; i < 150; i++) {
            
            //if (CustomerExec.currentPoolSize == CustomerExec.MaxPoolSize) {                
            //}//use a semaphore to control entrance to terminal (Break this in to 2 parts)
            
            customer cust = new customer(custID);//change///
            CustomerExec.submit(cust);            
            int custInterval = (int)( 100 + Math.random() *100);                        
            Thread.sleep(custInterval); //for demonstration purpose                        
            custID++;
            
            
        }
        }
        finally{
            if(CustomerExec != null) {
                CustomerExec.shutdown();
            }
                CustomerExec.awaitTermination(1, TimeUnit.MINUTES);
        }
        
        
    }
}
 //1.Customers
    //The customer is callable future with an ID to check the status of (ticket, waiting area) at any time        
    //Generate customers by every 1-4 seconds sleep
    //submiting in a for loop to a cached executor
    //--------------------------------------------------------------------------------------------------------------------//

