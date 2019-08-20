package busterminal;

import java.util.concurrent.Semaphore;

public class InspectorScanner {
}    
    
class scanner1 {
    
    private final Semaphore scan1Sem = new Semaphore(1);
    
       protected class scanArea1 implements Runnable{
           
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


class Inpector {
    customer cust;

    public Inpector(customer cust) {
        this.cust = cust;
    }

    public void inspectTicket(int custID, int waitingArea){

    }
}

