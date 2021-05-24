package org.example;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = 2L;

    private String brand;
    private String model;
    private int productionYear;
    private int mileage;

    public Car() { }

    public Car(String brand, String model, int productionYear, int mileage) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Samochod: " + brand + " " + model + " rok produkcji: " + productionYear + " przebieg: " + mileage;
    }
}
