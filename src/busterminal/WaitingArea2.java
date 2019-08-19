
package busterminal;

import java.util.concurrent.Semaphore;

    public class WaitingArea2 {
        private static final Semaphore sem2 = new Semaphore(10, true);

        public synchronized void enterA2(customer cust){
            
            if(sem2.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 2 is full."
                + "\n\t\tCustomer# " + cust.id + " has to wait for vacancy...");                
            }
            
            try{
                sem2.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered Waiting Area 2");
            }
            catch (InterruptedException e) {}
        }

        public void leaveA2(customer cust) throws InterruptedException{
            //wait until bus is here then start adding to scan & inspect (join) then bus
            
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving Waiting Area 2...");
            sem2.release();
        }
        
        
    }

