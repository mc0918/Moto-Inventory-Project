package com.example.motoinventoryservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Motorcycle {

    private int id;
    @NotEmpty(message = "You must supply a value for VIN.")
    @Size(min = 5, max = 5, message = "VIN must be 5 characters in length.")
    private String vin;
    @NotEmpty(message = "You must supply a value for make.")
    private String make;
    @NotEmpty(message = "You must supply a value for model.")
    private String model;
    @NotEmpty(message = "You must supply a value for year.")
    @Size(min = 4, max = 4, message = "Year must be 4 digits.")
    private String year;
    @NotEmpty(message = "You must supply a value for color.")
    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motorcycle that = (Motorcycle) o;
        return id == that.id &&
                Objects.equals(vin, that.vin) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                Objects.equals(year, that.year) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vin, make, model, year, color);
    }
}
