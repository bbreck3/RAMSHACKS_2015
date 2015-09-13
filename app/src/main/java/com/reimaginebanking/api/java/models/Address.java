package com.reimaginebanking.api.java.models;

import com.google.gson.annotations.SerializedName;
import com.reimaginebanking.api.java.Constants.AccountType;

/**
 * Created by kco942 on 4/9/15.
 */
public class Address {

    private String state;
    @SerializedName("street_name")
    private String streetName;
    @SerializedName("street_number")
    private String streetNumber;
    private String city;
    private String zip;

    public Address(String state, String streetName, String streetNumber, String city, String zip) {
        this.state = state;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber(){
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
