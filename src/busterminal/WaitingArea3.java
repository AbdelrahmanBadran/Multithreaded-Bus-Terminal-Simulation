
package busterminal;

import java.util.concurrent.Semaphore;

    public class WaitingArea3 {
        private static final Semaphore semA3 = new Semaphore(10, true);
        
        public synchronized void enterA3(customer cust){
           
            if(semA3.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 3 is full."
                + "\n\t\tCustomer# " + cust.id + " has to wait for vacancy...");
            }
            
            try{
                semA3.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered Waiting Area 3");
            }
            catch (InterruptedException e) {}
        }

        public void leaveA3(customer cust) throws InterruptedException{
            //wait until bus is here then start adding to scan & inspect (join) then bus
            
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving Waiting Area 3...");
            semA3.release();
        }
    }
