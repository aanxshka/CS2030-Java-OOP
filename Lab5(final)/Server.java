import java.util.function.Supplier;

class Server { 
    private final double time;
    private final int id;
    private final int qmax;
    private final ImList<Customer> customers;
    private final Supplier<Double> restTimes;

    Server(double time, int id, int qmax, ImList<Customer> customers, 
            Supplier<Double> restTimes) {  
        this.time = time;
        this.id = id;
        this.qmax = qmax;
        this.customers = customers;
        this.restTimes = restTimes;
    }


    int getPrev() {
        return this.id - 1;
    }

    ImList<Customer> getCustomers() {
        return this.customers;
    }

    int getqmax() {
        return this.qmax;
    }

    double getTime() {
        return this.time;
    }

    int getID() {
        return this.id;
    }


    Supplier<Double> getrestTimes() {
        return this.restTimes;
    }


    double getRT() {
        return this.restTimes.get();
    }
    
    Server queue(Customer c) {
        ImList<Customer> customers = this.customers.add(c);
        return new Server(this.time, this.id, this.qmax, customers, this.restTimes);
    }

    Server serve(Customer c, int scpos, int qn) {
        double t = c.getST();
        ImList<Customer> customers = this.customers;
        if (qn == 0) {
            t += c.getAT();
        } else {
            t += this.time;
            if (scpos == -1) {
                customers = customers.remove(0);
            }
        }
        return new Server(t, this.id, this.qmax, customers, this.restTimes); 
    }  

    double getMin() {
        return this.time;
    }


    int getMinPos() {
        return -1;
    }

    Server rest() {
        double newTime = this.time + this.getRT();
        return new Server(newTime, this.id, this.qmax, this.customers, this.restTimes);
    }

    String stringid() {
        return String.format("%d",this.id);
    }

    ImList<Server> getCounters() {
        return new ImList<Server>();
    }

    int getCounter(double t) {
        return this.id - 1;
    }

   
}
