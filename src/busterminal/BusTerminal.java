/**
 *
 * @author Abdelrahman Badran
 */

package busterminal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BusTerminal {
    
    public static void main(String[] args) throws InterruptedException {
        int custID = 1;
        
        //creating customers and submitting to thread pool
        ExecutorService CustomerExec = Executors.newFixedThreadPool(100);
     
        for (int i = 0; i < 150; i++) {
            //if (CustomerExec.currentPoolSize == CustomerExec.MaxPoolSize) {                
            //}//use a semaphore to control entrance to terminal (2 parts)
            
            customer cust = new customer(custID);//change///
            CustomerExec.submit(cust);
            
            int custInterval = (int)( 100 + Math.random() *100); ///change later///
            
            try{
                Thread.sleep(custInterval);
            }
            catch (InterruptedException e) {}

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

