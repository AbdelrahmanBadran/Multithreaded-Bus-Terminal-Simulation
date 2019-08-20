package busterminal;

import java.util.concurrent.ExecutionException;

public class customer implements Runnable{
    
    ticket ticket = new ticket();
    ticketMachine tMachine = new ticketMachine();
    ticketCounter1 tCounter1 =new ticketCounter1();
    ticketCounter2 tCounter2 =new ticketCounter2();
    WaitingArea1 wArea1 = new WaitingArea1(this);
    WaitingArea2 wArea2 = new WaitingArea2();
    WaitingArea3 wArea3 = new WaitingArea3();    
    
    int id;
    int ticketNo = ticket.ticketID;
    int waitingArea;
    boolean scanned;
    boolean inspected;        
    

    protected customer() {
    }
    
    protected customer(int id) {
        this.id = id;
    }

    protected customer(int id, int ticketNo) {
        this.id = id;
        this.ticketNo = ticketNo;
    }
    
    protected customer(int id, int ticketNo, int waitingArea) {
        this.id = id;
        this.ticketNo = ticketNo;
        
    }    

    public customer(int id, boolean scanned, boolean inspected, int waitingArea) {
        this.id = id;
        this.scanned = scanned;
        this.inspected = inspected;
        this.waitingArea = waitingArea;
    }
        
    @Override
    public synchronized void run(){
        try {
            System.out.println("\nCustomer #" + id + " Enters the Bus Terminal Foyer");
            getATicket();
            goToWatingArea();
            
        } catch (ExecutionException | InterruptedException ex) {}
        
    }
    
     protected void getATicket() throws ExecutionException, InterruptedException{
        
        int getTicketFrom = (int )( 1 + Math.random() *3  );
        
        switch (getTicketFrom) {
            case 1:
                System.out.println("\nCustomer #" + id + " is going to queue at Ticket Machine...");
                ticket t = tMachine.buyMTicket(this);
                if (t == null){
                    getATicket();
                }
                else{
                    this.ticketNo = t.ticketID;
                }
                break;
                
            case 2:
                System.out.println("\nCustomer #" + id + " is going to queue at Ticket Counter 1...");
                ticket c1 = tCounter1.buyC1Ticket(this);
                if (c1 == null){
                    getATicket();
                }
                else{
                    this.ticketNo = c1.ticketID;
                }
                break;
                
            case 3:
                System.out.println("\nCustomer #" + id + " is going to queue at Ticket Counter 2...");
                ticket c2 = tCounter2.buyC2Ticket(this);
                if (c2 == null){
                    getATicket();
                } 
                else{
//                    this.ticket = c2;
                    this.ticketNo = c2.ticketID;
                }
                break;
        }
     }
     
     
     protected void goToWatingArea() throws InterruptedException{
        switch (this.ticketNo) {
            case 1:
                wArea1.enterA1(this);
                //wArea1.leaveA1(this);
                break;
            case 2:
                wArea2.enterA2(this);
                //wArea2.leaveA2(this);
                break;
            default:
                wArea3.enterA3(this);
                //wArea3.leaveA3(this);
                break;
        }
     }
}
    //The customer class : get ticket --> go to waiting area (1-3)
    //if in waiting --> When bus arrives: scan and inspect ticket in any order 
    //-> then to bus   