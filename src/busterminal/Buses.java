//package busterminal;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.Semaphore;
//
//public class Buses {    
//}
//
//class Bus1{
//    private final Semaphore bus1Cap = new Semaphore(12);
//
//    ScheduledThreadPoolExecutor bus1Exec 
//  = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
//    bus1Exec.scheduledWithFixedDelay( ()-> System.out.println("Fixed Delay Schedule"), 2, 5000, TimeUnit.MILLISECONDS);
//      ScheduledExecutorService bus1Exec = Executors.newScheduledThreadPool(1);
//   public Lock busLock = new ReentrantLock();
//    public Condition customersFull = busLock.newCondition();
//    public Condition busArrives = busLock.newCondition();
//    
//    CyclicBarrier busBarrier = new CyclicBarrier(12);    
//    
//    
//    ScheduledExecutorService bus1Exec = Executors.newSingleThreadScheduledExecutor();        
//    customer cust;
//
//    public Bus1(customer cust) {
//        this.cust = cust;
//    }       
//
//      protected class runBus1 implements Runnable{
//           @Override
//           public synchronized void run(){       
//               
//               System.out.println("Customer # " + cust.id + "is boarding the scheduled bus at Area 1");
//           }
//       }    
//   }
