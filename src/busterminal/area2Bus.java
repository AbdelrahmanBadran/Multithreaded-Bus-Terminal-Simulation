
package busterminal;

import java.util.concurrent.ArrayBlockingQueue;

public class area2Bus implements Runnable{    
    customer passenger;
    int id;        
    ArrayBlockingQueue<customer> passBus2 = new ArrayBlockingQueue<>(10);     
    
    WaitingArea2 WA2 = new WaitingArea2(2);        

    public area2Bus(int id) {        
        this.id = id;        
    }                    

    //board the bus function
    
    @Override
    public void run(){                     
        int passCount;
        
        try {Thread.sleep(1000);} 
        catch (InterruptedException ex) {}
        
        while(true){                                        
            WA2.busIsHere();
            passCount = 0;      

            try{         
            Thread.sleep(randInterval());
            }catch (InterruptedException e) {}    
                                    
            while(passCount < 12){ //!passBus2.isEmpty()){ //|| passCount < 12                                    
                System.out.println("\n\n\tBus " + this.id +" is waiting for " + (12 - passCount) + " passengers at Area" + this.id);
                
                try {
                    passenger = passBus2.take();
                } catch (InterruptedException e) {}                   
                
                WA2.releaseOne(passenger);                

                System.out.println("\n\n\tCustomer # " + passenger.id + 
                        " is boarding Area " + this.id + " Bus"); 
                passCount++;

                if (passCount == 12){
                    System.out.println("\n\n\tAll 12 Passengers on Board"+
                            "\n\tBus for Area" + this.id +" is Departing!"); 
                    passCount = 0;
                    break;
                }
            }                     
            //deletes all previous passengers
            passBus2.clear();
            }                          
        }                   
        
        synchronized int randInterval(){            
            return (int)( 500 + Math.random() *1000); 
        }
        
        synchronized void put(customer cust) throws InterruptedException{
            passBus2.put(cust);
        }
        
        synchronized boolean offer(customer cust){       
            return passBus2.offer(cust);
        }
        
        synchronized boolean boardBus(customer cust, int busID){
            if(cust.scanned && cust.inspected){
                return passBus2.offer(cust);
            } else { 
                return false;
            }
        }
    }
    
