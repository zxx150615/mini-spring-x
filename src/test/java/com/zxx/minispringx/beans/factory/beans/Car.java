package com.zxx.minispringx.beans.factory.beans;

public class Car {

    private String brand;

    private Person person;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
