package busterminal;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Abdelrahman Badran
 */
public class WaitingAreas {
    int AreaID;

    public WaitingAreas() {
    }

    public WaitingAreas(int AreaID) {
        this.AreaID = AreaID;
    }
}

    class waitingArea1 {
        Semaphore sem = new Semaphore(10, true);

        public void enterA1(int custID){
            if(sem.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 1 is full."
                + "\n\t\tCustomer# " + custID + " has to wait for vacancy...");
            }
                try{
                    sem.acquire();
                    System.out.println("\nCustomer# " + custID + " entered"
                    + " Waiting Area 1");
            }
            catch (Exception e) {}
        }

        public void leaveA1(int custID) throws InterruptedException{
            //When bus is here
            sem.release();
            Thread.sleep(1000);
            System.out.println("\n\tCustomer# " + custID + " is leaving "
                    + " Waiting Area 1...");
        }
    }

    class waitingArea2 {
        Semaphore sem = new Semaphore(10, true);

        public void enterA2(int custID){
            if(sem.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 2 is full."
                + "\n\t\tCustomer# " + custID + " has to wait for vacancy...");
            }
                try{
                    sem.acquire();
                    System.out.println("\nCustomer# " + custID + " entered"
                    + " Waiting Area 2");
            }
            catch (Exception e) {}
        }

        public void leaveA2(int custID) throws InterruptedException{
            //When bus is here
            sem.release();
            Thread.sleep(1000);
            System.out.println("\n\tCustomer# " + custID + " is leaving "
                    + " Waiting Area 2...");
        }
    }

    class waitingArea3 {
        Semaphore sem = new Semaphore(10, true);

        public void enterA3(int custID){
            if(sem.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 3 is full."
                + "\n\t\tCustomer# " + custID + " has to wait for vacancy...");
            }
                try{
                    sem.acquire();
                    System.out.println("\nCustomer# " + custID + " entered"
                    + " Waiting Area 3");
            }
            catch (Exception e) {}
        }

        public void leaveA3(int custID) throws InterruptedException{
            //When bus is here
            sem.release();
            Thread.sleep(1000);
            System.out.println("\n\tCustomer# " + custID + " is leaving "
                    + " Waiting Area 3...");
        }
    }


//(3 shared): Max 10 Pax each (Customer come here depends on ticket no.)
//*(3 objects) A single thread pool for each that has a reverse latch concept
//until bus arrives then they are passed to scanner and inspector

//only leave if there is an available place for them based on scanned (use atomic)
