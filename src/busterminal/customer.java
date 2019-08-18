package busterminal;

import java.util.concurrent.ExecutionException;

public class customer implements Runnable{
    int id;
    ticketMachine tMachine = new ticketMachine();
    ticketCounter1 tCounter1 =new ticketCounter1();
    ticketCounter2 tCounter2 =new ticketCounter2();
    waitingArea1 wArea1 = new waitingArea1();
    waitingArea2 wArea2 = new waitingArea2();
    waitingArea3 wArea3 = new waitingArea3();

    ticket ticket = new ticket();
    
    int ticketNo; //change
    int waitingArea; //change

    public customer(int id) {
        this.id = id;
//        this.tMachine = tMachine;
//        this.ticketNo = ticketNo;
//        this.waitingArea = waitingArea;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("\nCustomer #" + id + " Enters the Bus terminal");
            getATicket();
            goToWatingArea();
            
        } catch (ExecutionException | InterruptedException ex) {}
        return;
    }
    
     public void getATicket() throws ExecutionException, InterruptedException{
        int getTicketFrom = (int )( 1 + Math.random() *3  );///change////
        
        switch (getTicketFrom) {
            case 1:
                System.out.println("\nCustomer #" + id + " is going to Ticket Machine...");
                ticket t = tMachine.buyMTicket(id);
                if (t == null){
                    System.out.println("\n\tCustomer # " + id + " didn't get a ticket...trying again");
                    getATicket();
                }
                else{
                    this.ticket = t;
                    this.ticketNo = t.ticketID;
                }
                break;
                
            case 2:
                System.out.println("\nCustomer #" + id + " is going to Ticket Counter 1...");
                ticket c1 = tCounter1.buyC1Ticket(id);
                if (c1 == null){
                    System.out.println("\n\tCustomer # " + id + " didn't get a ticket...trying again");
                    getATicket();
                }
                else{
                    this.ticket = c1;
                    this.ticketNo = c1.ticketID;
                }
                break;
                
            case 3:
                System.out.println("\nCustomer #" + id + " is going to Ticket Counter 2...");
                ticket c2 = tCounter2.buyC2Ticket(id);
                if (c2 == null){
                    System.out.println("\n\tCustomer # " + id + " didn't get a ticket...trying again");
                    getATicket();
                } 
                else{
                    this.ticket = c2;
                    this.ticketNo = c2.ticketID;
                }
                break;
        }
        return;
     }
     
     
     protected void goToWatingArea() throws InterruptedException{
         if (this.ticketNo == 1){
            wArea1.enterA1(id);
            wArea1.leaveA1(id);
         }
         else if (ticketNo == 2){
            wArea2.enterA2(id);
            wArea2.leaveA2(id);
         }
         // (ticketNo == 3)
         else{
            wArea3.enterA3(id);
            wArea3.leaveA3(id);
         }
         return;
     }
    
    //The customer class : get ticket --> go to waiting area (1-3)
    //if has ticket --> When bus arrives: scan and inspect ticked in any order -> then to bus   
}

//    @Override
//    public List call() throws Exception {
//        return List
//    }
