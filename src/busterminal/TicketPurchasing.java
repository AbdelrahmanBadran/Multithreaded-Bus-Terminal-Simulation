package busterminal;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TicketPurchasing{
    //instantiate each object
    //xecutorService tMachine = Executors.newFixedThreadPool(3);
}

class ticket { 

    public ticket() {}
    
    int ticketID;
    
    public ticket(int ticketID) {
        this.ticketID = ticketID;
    }
}

class ticketMachine{
    
    ExecutorService tickMachine = Executors.newSingleThreadExecutor();
    tMachinePrinter tickMPrinter = new tMachinePrinter();
    
    boolean success;
    ticket ticket;

    public ticket buyMTicket(int custID) throws InterruptedException, ExecutionException{
                
        Random rnd = new Random();
        boolean down = rnd.nextDouble() <= 0.3;
        
        if (down) {
            System.out.println("\n\n\t\tSorry...Ticket Machine is Down!....\n");
            return null;
        }
            System.out.println("\n\tCustomer # " + custID+ " is using the Ticket Machine...");

          Future<ticket> newTicket = tickMachine.submit(tickMPrinter);
          try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

          if(newTicket.isDone()){
            System.out.println("\n\t\tCustomer # " + custID+ " got a ticketID: " + newTicket.get().ticketID);
            return newTicket.get();
          }
          else{
            return null;
        }
    }
    
    class  tMachinePrinter implements Callable<ticket>{
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
    
    boolean success;
    ticket ticket;

    public ticket buyC1Ticket(int custID) throws InterruptedException, ExecutionException{
                        
        Random rnd = new Random();
        boolean bbreak = rnd.nextDouble() <= 0.15;
        
        if (bbreak) {
            System.out.println("\n\n\t\tSorry...Counter 1 Personnel is on break!....\n");
            return null;
        }
        System.out.println("\n\tCustomer # " + custID+ " is using Ticket Counter 1...");
        
        Future<ticket> newTicket = tickCounter1.submit(tickC1Printer);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        
        if(newTicket.isDone()){
        System.out.println("\n\t\tCustomer # " + custID+ " got a ticketID: " + newTicket.get().ticketID);
        return newTicket.get();
        }
        else{
            return null;
        }
    }       
}

//Counter 2 class to get a ticket from the counter
class ticketCounter2 {
    ExecutorService tickCounter2 = Executors.newSingleThreadExecutor();
    tCounterPrinter tickC2Printer = new tCounterPrinter();
    
    boolean success;
    ticket ticket;

    public ticket buyC2Ticket(int custID) throws InterruptedException, ExecutionException{
        Random rnd = new Random();
        boolean bbreak = rnd.nextDouble() <= 0.15;
        
        if (bbreak) {
            System.out.println("\n\n\t\tSorry...Counter 2 Personnel is on break!....\n");
            return null;
        }
        
        System.out.println("\n\tCustomer # " + custID+ " is using Ticket Counter 2...");
        
        Future<ticket> newTicket = tickCounter2.submit(tickC2Printer);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        
        if(newTicket.isDone()){
        System.out.println("\n\t\tCustomer # " + custID+ " got a ticketID: " + newTicket.get().ticketID);
        return newTicket.get();
        }
        else{
            return null;
        }
    }       
}

    class  tCounterPrinter implements Callable<ticket>{
        @Override
        public synchronized ticket call() throws Exception {
            int ticketID = (int )( 1 + Math.random() * 3);
            ticket ticket = new ticket(ticketID);
            return ticket;
        }
    }

//public class TicketArea {
//    ExecutorService tMachine = Executors.newFixedThreadPool(3);
//
//}
    //2. Get ticket: FixedPool of 3  //Receive Customers and give them a ticket

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