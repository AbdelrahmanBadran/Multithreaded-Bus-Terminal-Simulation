package busterminal;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPurchasing{
    
//    ticketMachine tMachine = new ticketMachine();
//    ticketCounter1 tCounter1 = new ticketCounter1();
//    ticketCounter2 tCounter2 =new ticketCounter2();
//    ExecutorService allTickets = Executors.newFixedThreadPool(3);
}

 class ticket { 
    int ticketID;
    
    public ticket(){}        
    
    public ticket(int ticketID) {
        this.ticketID = ticketID;
    }
}

class ticketMachine{
    
    ExecutorService tickMachine = Executors.newSingleThreadExecutor();
    tMachinePrinter tickMPrinter = new tMachinePrinter();
    
    private static final ReentrantLock tMachineLock  = new ReentrantLock(true);
    
    ticket ticket;
    
    protected ticket buyMTicket(customer cust ) throws InterruptedException, ExecutionException{
        tMachineLock.lock();
        try{
        Random rnd = new Random();
        boolean down = rnd.nextDouble() <= 0.2;
        
        if (down) {
            System.out.println("\n\n\t\tSorry Customer # " + cust.id + "...Ticket Machine is Down!....\n");
            Thread.sleep(250);
            System.out.println("\n\tYes, Ticket Machine has been is fixed");
//        int getTicketFrom = (int )( 1 + Math.random() *2 ); //send customers to booth
        }
            
        System.out.println("\n\tCustomer # " + cust.id + " is now using the Ticket Machine...");
          
        Future<ticket> newTicket = tickMachine.submit(tickMPrinter);
        Thread.sleep(500);

        if(newTicket.isDone()){
            System.out.println("\n\t\tCustomer # " + cust.id + " got a ticketID: " + newTicket.get().ticketID);
            ticket = newTicket.get();
        }
        else{
            System.out.println("\n\tCustomer # " + cust.id  + " did not get a ticket...");
        }
          
        } finally{
            tMachineLock.unlock();  
        }        
        return ticket;
        
    }
    
    private class tMachinePrinter implements Callable<ticket>{
        @Override
        public synchronized ticket call() throws Exception {
            int ticketID = (int )( 1 + Math.random() *3);
            ticket ticket = new ticket(ticketID);
            return ticket;
        }
    }
}

class ticketCounter1{
    
    ExecutorService tickCounter1 = Executors.newSingleThreadExecutor();
    tCounterPrinter tickC1Printer = new tCounterPrinter();
    
    private static final ReentrantLock tCounter1Lock  = new ReentrantLock(true);
    
    ticket ticket = null;

    protected ticket buyC1Ticket(customer cust) throws InterruptedException, ExecutionException{
       
        tCounter1Lock.lock();
        
        try{    
            Random rnd = new Random();
            boolean bbreak = rnd.nextDouble() <= 0.1;
            
            if (bbreak) {
                System.out.println("\n\n\t\tSorry Customer # " + cust.id+ " Counter 1 Personnel is on break!....will be back Shortly\n");
                Thread.sleep(250);
            }

            System.out.println("\n\tCustomer # " + cust.id+ " is now at Ticket Counter 1...");

            Future<ticket> newTicket = tickCounter1.submit(tickC1Printer);
            Thread.sleep(500);

            if(newTicket.isDone()){
                 System.out.println("\n\t\tCustomer # " + cust.id+ " got a ticketID: " + newTicket.get().ticketID);
               ticket = newTicket.get();

            }
            else{
                System.out.println("\n\tCustomer # " + cust.id + " did not get a ticket...");
            }
            
        } finally{
            tCounter1Lock.unlock();
        }
        return ticket;
    }       
}

//Counter 2 class to get a ticket from the counter
class ticketCounter2 {
    ExecutorService tickCounter2 = Executors.newSingleThreadExecutor();
    tCounterPrinter tickC2Printer = new tCounterPrinter();
    
    private static final ReentrantLock tCounter2Lock  = new ReentrantLock(true);
    
    ticket ticket = null;
    
    protected ticket buyC2Ticket(customer cust) throws InterruptedException, ExecutionException{
        tCounter2Lock.lock();
        try{
        
        Random rnd = new Random();
        boolean bbreak = rnd.nextDouble() <= 0.1;
        
        if (bbreak) {
            System.out.println("\n\n\t\tSorry Customer # " + cust.id + " Counter 2 Personnel is on break!.....will be back Shortly\n");
            Thread.sleep(250);
        }
        
        System.out.println("\n\tCustomer # " + cust.id + " is now at Ticket Counter 2...");

        Future<ticket> newTicket = tickCounter2.submit(tickC2Printer);
        Thread.sleep(500);
        
        if(newTicket.isDone()){
            System.out.println("\n\t\tCustomer # " + cust.id + " got a ticketID: " + newTicket.get().ticketID);
            ticket = newTicket.get();
        }
        else{
            System.out.println("\n\tCustomer # " + cust.id + " did not get a ticket...");
        }
        
        } finally{
            tCounter2Lock.unlock();
        }
        return ticket;
    }       
}

    class tCounterPrinter implements Callable<ticket>{
        @Override
        public synchronized ticket call() throws Exception {
            int ticketID = (int )( 1 + Math.random() * 3);
            ticket ticket = new ticket(ticketID);
            return ticket;
        }
    }

    //Ways to handle customer queue: 1TMachine 1TCounter,Randomly here and there, 
    //or based on the number in the queue of TM
    //Ticket passed to customer callable future with id 1,2, or 3 for waiting area   
    
    //periodicaly break one down or Random rand = new Random ();
    //if it is true, we get a ticket
    //if false returns null and the ticket machine is down
    //should send customer to counter and vice versa        

//*All 3 of Ticket MC passed to a fixed thread pool of 3 (break them with intervals in each obj)
//Ticket Counter (2 shared): Employee short toilet breaks (timer-task)
//*Calable & Future to generate the tickets & based on ticket number returned (1+ (rand () %3)) go to