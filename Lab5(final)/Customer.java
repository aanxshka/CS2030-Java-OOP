
import java.util.function.Supplier;

class Customer {

    private final Supplier<Double> serviceTime;
    private final double arrivalTime;
    private final int id;


    Customer(double arrivalTime, Supplier<Double> serviceTime,  int id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.id = id;
    }

   

    double getAT() {
        return this.arrivalTime;
    }


    int getID() {
        return this.id;
    }

    double getST() {
        return this.serviceTime.get();
    }

}



