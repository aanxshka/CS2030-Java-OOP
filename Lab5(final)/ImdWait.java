class ImdWait implements Event {

    private final Customer c;
    private final double time;
    private final int id;
    private final String str;
    private final int num;

    ImdWait(Customer c, double time, int id, String str, int num) {
        this.c = c;
        this.time = time;
        this.id = id;
        this.str = str;
        this.num = num;

    } 
  
    public double getTime() {
        return this.time;
    }
 
    public Customer getCustomer() {
        return this.c;
    }
 
    public double getWaitTime() {
        return 0.0;
    }

 
    public int getServed() {
        return 0;
    }

    public int getNotServed() {
        return 0;
    }


    public Pair<Event,ImList<Server>> nextEvent(ImList<Server> servers) {
        int index = this.id - 1;
        Server ser = servers.get(index);
        double stime = ser.getTime();
        int scpos = -1;
        String newstr = this.str;

        if (this.num != 0 && this.id == servers.size()) {
            Server sc = servers.get(servers.size() - 1);
            stime = sc.getMin();
            if (sc.getCounter(this.time) == -1) {
                return new Pair<Event, ImList<Server>>(new ImdWait(this.c, stime, this.id, 
                            this.str, this.num),servers);
            } else {
                scpos = sc.getMinPos();
                newstr = String.format("self-check %d", servers.size() + scpos);

            }
        }
        

        if (ser.getCustomers().get(0).getID() == this.c.getID() && 
                this.time >= stime) {
            return new Pair<Event, ImList<Server>>(new Serve(this.c,
                        stime, this.id, newstr, scpos, 1), servers);
        }
        return new Pair<Event, ImList<Server>>(new ImdWait(this.c,
                stime, this.id, this.str, this.num), servers);
    }


    

    @Override
    public String toString() {        
        return "";
    }



}

