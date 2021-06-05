package org.example;

public class Travel {
    private long id;
    private long clientId;
    private long placeId;
    private int numberOfDays;
    private float price;

    public Travel() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", placeId=" + placeId +
                ", numberOfDays=" + numberOfDays +
                ", price=" + price +
                '}';
    }
}
