
package busterminal;

import java.util.concurrent.Semaphore;

public class scannerArea2 {    
    int scannerID;        
    area2Bus bus2 = new area2Bus(2);
    WaitingArea2 WA2 = new WaitingArea2(2);
           
    public scannerArea2(int scannerID) {
        this.scannerID = scannerID;
    }
    
        protected static Semaphore scA2 = new Semaphore(1, true);
        
        synchronized void scanA2(customer cust) throws InterruptedException{     
            try{
            scA2.acquire();        

            cust.scanned = true;
            Thread.sleep(150);
            System.out.println("\n\t\tCustomer # " + cust.id + " scanned ticket in Area " + this.scannerID);

//            if (cust.scanned){ //&& cust.inspected){
//                bus2.put(cust);                 
//                WA2.remove(cust);
//            }
            }
             finally{
                if(scA2.availablePermits() < 1) {
                    scA2.release();
                }
        }
    }
}