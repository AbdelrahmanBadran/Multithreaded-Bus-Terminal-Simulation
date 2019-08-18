package busterminal;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Abdelrahman Badran
 */
public class WaitingAreas {
    int AreaID;

    public WaitingAreas() {}
    
    public WaitingAreas(int AreaID) {
        this.AreaID = AreaID;
    }
}

    class waitingArea1 {
        
        private static final Semaphore sem1 = new Semaphore(10, true); //1truee
        
        public void enterA1(customer cust) throws InterruptedException{
            if(sem1.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 1 is full."
                + "\n\t\tCustomer# " + cust.id  + " has to wait for vacancy...");
            }
            try{
                sem1.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered"
                + " Waiting Area 1");
            }
            catch (InterruptedException e) {}
        }

        public void leaveA1(customer cust) throws InterruptedException{
            //When bus is here
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving "
                    + " Waiting Area 1...");
            sem1.release();
        }
    }

    class waitingArea2 {
        private static final Semaphore sem2 = new Semaphore(10, true);

        public void enterA2(customer cust) throws InterruptedException{
            if(sem2.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 2 is full."
                + "\n\t\tCustomer# " + cust + " has to wait for vacancy...");
                
            }
            try{
                sem2.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered"
                + " Waiting Area 2");
            }
            catch (InterruptedException e) {}
        }

        public void leaveA2(customer cust) throws InterruptedException{
            //When bus is here
            
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving "
                    + " Waiting Area 2...");
            sem2.release();
        }
    }

    class waitingArea3 {
        private static final Semaphore sem3 = new Semaphore(10, true);
        
        public void enterA3(customer cust){
           
            if(sem3.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 3 is full."
                + "\n\t\tCustomer# " + cust.id + " has to wait for vacancy...");
            }
            try{
                sem3.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered"
                + " Waiting Area 3");
            }
            catch (InterruptedException e) {}
        }

        public void leaveA3(customer cust) throws InterruptedException{
            //When bus is here
            
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving "
                    + " Waiting Area 3...");
            sem3.release();
        }
    }


//(3 shared): Max 10 Pax each (Customer come here depends on ticket no.)
//*(3 objects) A single thread pool for each that has a reverse latch concept
//until bus arrives then they are passed to scanner and inspector

//only leave if there is an available place for them based on scanned (use atomic)
