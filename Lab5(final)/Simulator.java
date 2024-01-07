import java.util.Comparator;
import java.util.function.Supplier;

class Simulator {

    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;   
    private final Supplier<Double> restTimes;    

    Simulator(int numOfServers, int numOfSelfChecks, int qmax,  ImList<Double> arrivalTimes, 
            Supplier<Double> serviceTimes, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.restTimes = restTimes;
    }


    ImList<Event> eventslist() {

        // list of servers
        ImList<Server> servers = new ImList<Server>();
        for (int i = 1; i <= this.numOfServers; i++) {
            Server s = new Server(0.0, i, qmax, new ImList<Customer>(), this.restTimes);
            servers = servers.add(s); 
        }

        ImList<Server> counters = new ImList<Server>();
        for (int i = 0; i < this.numOfSelfChecks; i++) {
            Server co = new Server(0.0, this.numOfServers + 1 + i, qmax, new ImList<Customer>(),
                    this.restTimes);
            counters = counters.add(co);
        }

        SelfCheckout sc = new SelfCheckout(0.0, this.numOfServers + 1, qmax, new ImList<Customer>(),
                this.restTimes, counters, this.numOfServers + 1);
        
        if (numOfSelfChecks == 0) {
            servers = servers;
        } else {
            servers = servers.add(sc);
        }
        
        //list of customers 
        ImList<Customer> customers = new ImList<Customer>();
        int size = this.arrivalTimes.size();
        for (int i = 0; i < size; i++) {
            Customer c = new Customer(this.arrivalTimes.get(i), this.serviceTimes, i + 1);
            customers = customers.add(c);
        }

        //list of events 
        ImList<Event> events = new ImList<Event>();
    
        PQ<Event> pq = new PQ<>(new TimeComp());

        for (Customer c : customers) {
            Arrival arr = new Arrival(c, this.numOfSelfChecks);
            pq = pq.add(arr);
        }

        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pair = pq.poll();
            Event e = pair.first();
            pq = pair.second();
            

            Pair<Event, ImList<Server>> output = e.nextEvent(servers);
            Event next = output.first();
            servers = output.second();

            if (e == next) {
                pq = pq;
            } else {
                pq = pq.add(next);
            }
           
            events = events.add(e);
            
        }

      
        return events;

    }



    String simulate() {
        return this.toString();
    }
        
    @Override
    public String toString() {
        ImList<Event> events = eventslist();
        double waittime = 0.0;
        int served = 0;
        int notserved = 0; 
        String str = events.get(0).toString();
        events = events.remove(0);
        for (Event e : events) {
            String s = e.toString();
            if (s != "") {
                str = str + "\n" + e.toString();
            }

            served += e.getServed();
            notserved += e.getNotServed();
            waittime += e.getWaitTime();
            
        }   
        double avgtime = 0.0;
        if (served != 0) {
            avgtime = (double) waittime / served;
        }

        str = str + "\n" + "[" + String.format("%.3f", avgtime) + " "
            + served  + " " + notserved + "]";
        return str;
    }  


}
