class Leave implements Event {

    private final Customer c;
    private final double time;
    
    Leave(Customer c, double time) { 
        this.c = c;
        this.time = time;
    }

    public double getWaitTime() {
        return 0.0;
    }


    public int getServed() {
        return 0;
    }

    public int getNotServed() {
        return 1;
    }

    public double getTime() {
        return this.time;
    }


    public Customer getCustomer() {
        return this.c;
    }

    public Pair<Event,ImList<Server>> nextEvent(ImList<Server> servers) {
        Event next = this;
        return new Pair<>(next, servers);
    }


    @Override
    public String toString() {
        return String.format("%.3f", this.c.getAT()) + " " + this.c.getID() + " leaves";
    }

}
