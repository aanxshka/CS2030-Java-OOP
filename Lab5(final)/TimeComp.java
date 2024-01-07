import java.util.Comparator;

class TimeComp implements Comparator<Event> {
    public int compare(Event a, Event b) {
        if (b.getTime() == a.getTime()) {
            return a.getCustomer().getID() - b.getCustomer().getID();
        } else if (b.getTime() < a.getTime()) {
            return 1;
        } else if (b.getTime() > a.getTime()) {
            return -1;
        }
        return 0;
    }
}

