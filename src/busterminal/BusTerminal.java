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
        
        //creating customers and submitting to thread pool
        ExecutorService CustomerExec = Executors.newFixedThreadPool(100);
     
        for (int i = 0; i < 150; i++) {
            //if (CustomerExec.currentPoolSize == CustomerExec.MaxPoolSize) {                
            //}
            
            customer cust = new customer(custID);//change///
            CustomerExec.submit(cust);
            
            int custInterval = (int)( 100 + Math.random() *1000  ); ///change later///
            
            try{
                Thread.sleep(custInterval);
            }
            catch (Exception e) {}

            custID++;
        }
        
        CustomerExec.shutdown();
    }
}

//For schedueled bus
//        ScheduledThreadPoolExecutor executor = 
//        (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
//        Task task = new Task();
//        executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);

 //1.Customers
    //The customer is callable future with an ID to check the status of (ticket, waiting area) at any time        
    //Generate customers by every 1-4 seconds sleep
    //submiting in a for loop to a cached executor
    //--------------------------------------------------------------------------------------------------------------------//

