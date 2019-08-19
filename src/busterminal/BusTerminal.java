/**
 *
 * @author Abdelrahman Badran
 */
package busterminal;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BusTerminal {
    public Lock busLock = new ReentrantLock();
    public Condition customersFull = busLock.newCondition();
    public Condition busArrives = busLock.newCondition();
    
    CyclicBarrier busBarrier = new CyclicBarrier(12);    
    
    public static void main(String[] args) throws InterruptedException {
        int custID = 1;
                
        ExecutorService CustomerExec = Executors.newFixedThreadPool(100);
     
        for (int i = 0; i < 150; i++) {
            
            //if (CustomerExec.currentPoolSize == CustomerExec.MaxPoolSize) {                
            //}//use a semaphore to control entrance to terminal (Break this in to 2 parts)
            
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
 //1.Customers
    //The customer is callable future with an ID to check the status of (ticket, waiting area) at any time        
    //Generate customers by every 1-4 seconds sleep
    //submiting in a for loop to a cached executor
    //--------------------------------------------------------------------------------------------------------------------//

