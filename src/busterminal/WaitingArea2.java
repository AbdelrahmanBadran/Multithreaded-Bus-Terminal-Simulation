
package busterminal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;

    public class WaitingArea2 {
        
         ExecutorService busSchedule = null;
            
        ArrayBlockingQueue<customer> custQueue = new ArrayBlockingQueue<customer>(12);
        private static final Semaphore semA2 = new Semaphore(10, true);
        CountDownLatch bus2Latch = new CountDownLatch(1);        
                
        public void startBus2(){                                 
            busSchedule = Executors.newSingleThreadExecutor(); //scheduled
            busArea2 busA2 = new busArea2();//change///        
            busSchedule.submit(busA2);
        }
        
        public synchronized void enterA2(customer cust){                       
            
            if(semA2.availablePermits() < 1) {
                System.out.println("\n\n\tSorry, waiting area 2 is full."
                + "\n\t\tCustomer# " + cust.id + " has to wait for vacancy...");                
            }
            
            try{
                semA2.acquire();
                System.out.println("\nCustomer# " + cust.id  + " entered Waiting Area 2");
                custQueue.put(cust);
                bus2Latch.await();
                System.out.println("\n\n\tCustomer# " + cust.id  + " is leaving Waiting Area 2..."); 
            }
            catch (InterruptedException e) {}            
            //go to scanner then inspector or vv
            
            finally{
                semA2.release();
            }
            
        }

//        public void leaveA2(customer cust) throws InterruptedException{
//            //wait until bus is here then start adding to scan & inspect (join) then bus               
//            bus2Latch.countDown();
//                                  
//        }
        
//        ExecutorService busSchedule = Executors.newScheduledThreadPool(1);
             
       
        class busArea2 implements Runnable{
            customer cust;

            public busArea2() {
            }
            
            public busArea2(customer cust) {
                this.cust = cust;
            }                                    
            public void run(){
                int passCount = 0;
                
                try {                    
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
                
                System.out.println("\n\n\t\tArea #2 Bus has arrived!");
                bus2Latch.countDown();
                
                while(!custQueue.isEmpty() && passCount != 12){   
                    try {
                        cust = custQueue.take();
                    } catch (InterruptedException e) {
                    }
                    System.out.println("\n\n\tCustomer # " + cust.id + 
                            " is boarding Area 2 Bus");   
                    ++passCount;
                }                                
            }
        }
    }

