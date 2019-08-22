
package busterminal;

public class Inspector implements Runnable{    
    WaitingArea2 WA2 = new WaitingArea2(2);
    area2Bus bus2 = new area2Bus(2);
    customer current;
    
    @Override
    public void run() {        
        while(true){             
            //wait for bus 1 (lock condition) bus 2 bus 3
            if (!WA2.isEmpty()) {
                current = WA2.peek();                    
                System.out.println("\n\tInspector is Inspecting Customer # " 
                        + current.id + " with Ticket ID: " + current.ticketNo + " at area "+ current.ticketNo);            
                current.inspected = true;
                System.out.println("\n\t\tTicket inspected");

//                if (current.scanned && current.inspected) {
//                    try {
//                        bus2.put(current);
//                        WA2.remove(current);                              
//                    } catch (InterruptedException ex) {}
//                }
            }
        }               
    }
    
    void inspectTicket(customer cust){
        System.out.println("\n\tInspector is Inspecting Customer # " 
            + cust.id + " with Ticket ID: " + cust.ticketNo + " at area "+ cust.wArea2);            
        cust.inspected = true;
        System.out.println("\n\t\tTicket inspected");
    }
}   