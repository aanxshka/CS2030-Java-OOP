interface Event { 

    double getTime();

    double getWaitTime();

    Customer getCustomer();

    int getServed(); 

    int getNotServed();

    Pair<Event,ImList<Server>>  nextEvent(ImList<Server> servers);


    public String toString();

}
