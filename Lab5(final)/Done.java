class Done implements Event {

    private final Customer c;
    private final double time;
    private final int id;
    private final String str;

    Done(Customer c, double time, int id, String str) {
        this.c = c;
        this.time = time;
        this.id = id;
        this.str = str;
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


    public double getTime() {
        return this.time;
    }

    public Customer getCustomer() {
        return this.c;

    }


    public Pair<Event,ImList<Server>> nextEvent(ImList<Server> servers) {
        Server oldser = servers.get(this.id - 1);
        Server newser = oldser;
        
        if (oldser.getCounters().size() == 0) {
            newser = oldser.rest();
            servers = servers.set(this.id - 1, newser);
        }

        Event next = this;
        return new Pair<Event, ImList<Server>>(this, servers);
    }


    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + this.c.getID() 
            + " done serving by " + this.str;
    }

}
