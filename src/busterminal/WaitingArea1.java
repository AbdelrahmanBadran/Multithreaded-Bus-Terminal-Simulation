package busterminal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitingArea1 {    
    customer cust;
    public WaitingArea1() {
    }
    public WaitingArea1(customer cust) {
        this.cust = cust;
    }               
        
    //CyclicBarrier busBarrier = new CyclicBarrier(12);

    private final Lock bus1Lock = new ReentrantLock(true);
    private Condition fullPass = bus1Lock.newCondition();
    private Condition bus1Arrives = bus1Lock.newCondition();
        
    //array blocking queue of 12
    
    boolean IsBusHere = false;    
    protected int custCount = 0;    
    
    private final Semaphore sem1 = new Semaphore(10, true);

    public synchronized void enterA1(customer cust) throws InterruptedException{
        if(sem1.availablePermits() < 1) {
            System.out.println("\n\n\tSorry, waiting area 1 is full."
            + "\n\t\tCustomer# " + cust.id  + " has to wait for vacancy...");
        }
        try{
            sem1.acquire();
            custCount++;
            System.out.println("\nCustomer# " + cust.id  + " entered"
            + " Waiting Area 1");
        }
        catch (InterruptedException e) {}
        
        //can be a simple wait notify
        synchronized(this){
            bus1Lock.lock();
            try {
                while(custCount > - 1){
                    bus1Arrives.await();
                }
                sem1.release();
//            System.out.println("Customer # " + cust.id + "is boarding the scheduled bus at Area 1");
//            boardBus1(); //addToList
            
            } finally{
                bus1Lock.unlock();
            }
              
        }
    }
    
   // The Bus for Area 1
    public class bus1{
        customer cust;
        
        public bus1() {}

        public bus1(customer cust) {
            this.cust = cust;
        }        
        
        private final Semaphore bus1Max = new Semaphore(12);
        
        protected class runBus1 implements Runnable{
            @Override
            public void run(){
               boardBus1(cust);       
            }
        }

        protected void boardBus1(customer cust){
            bus1Lock.lock();
            try{
                while(bus1Max.availablePermits() > 1){
                    bus1Arrives.signalAll();

                }
                //full signal

            }finally{
                bus1Lock.unlock();
            }
           System.out.println("Customer # " + cust.id + "is boarding the scheduled bus at Area 1");
        }   

        public void leaveA1(customer cust) throws InterruptedException{
            //When bus is here
            System.out.println("\n\tCustomer# " + cust.id  + " is leaving "
                    + " Waiting Area 1...");
            sem1.release();
        }    
    }
}   
//wait until bus is here then start adding to scan & inspect (join) then bus

//(3 shared): Max 10 Pax each (Customer come here depends on ticket no.)
//*(3 objects) A single thread pool for each that has a reverse latch concept
//until bus arrives then they are passed to scanner and inspector
//only leave if there is an available place for them based on scanned (use atomic)

//        ScheduledThreadPoolExecutor executor = 
//        (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
//    