package busterminal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InspectorScanner {
    
}    
    
class scanner1 {
       private class scanArea1 implements Runnable{
            customer cust;

            public scanArea1(customer cust) {
                this.cust = cust;
            }
            
        @Override
        public synchronized void run(){            
            System.out.println("Customer # " + cust.id + "is scanning ticket in Area 1");
            cust.scanned = true;
        }
    }
    
}

class Inpector{
    int custID;
    int waitingArea;

    public Inpector(int custID, int waitingArea) {
        this.custID = custID;
        this.waitingArea = waitingArea;
    }

    public void inspectTicket(int custID, int waitingArea){

    }
}

