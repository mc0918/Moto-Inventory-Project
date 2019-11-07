package com.trilogyed.vinlookup.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Vehicle {
    @NotEmpty(message = "You must supply a value for type.")
    @Size(max = 20)
    private String type;
    @NotEmpty(message = "You must supply a value for make.")
    @Size(max = 20)
    private String make;
    @NotEmpty(message = "You must supply a value for model.")
    @Size(max = 20)
    private String model;
    @NotEmpty(message = "You must supply a value for year.")
    @Size(min = 4, max = 4)
    private String year;
    @NotEmpty(message = "You must supply a value for color.")
    @Size(max = 20)
    private String color;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
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
}
