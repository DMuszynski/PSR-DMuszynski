import java.io.Serializable;

public class Transport implements Serializable {

    private static final long serialVersionUID = 4L;

    private long driverId;
    private long passengerId;
    private float transportationCost;

    public Transport() { }

    public Transport(long driverId, long passengerId, float transportationCost) {
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.transportationCost = transportationCost;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    public float getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(float transportationCost) {
        this.transportationCost = transportationCost;
    }

    @Override
    public String toString() {
        return "Przejazd: id kierowcy: " + driverId + " id pasażera: " + passengerId + " koszt przejazdu: " + transportationCost;
    }
}
