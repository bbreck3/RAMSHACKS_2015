package com.reimaginebanking.api.java.Constants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hxp347 on 5/6/15.
 */
public enum AccountType {
    @SerializedName("Credit Card")
    CREDITCARD("Credit Card"),
    @SerializedName("Saving")
    SAVINGS("Savings"),
    @SerializedName("Checking")
    CHECKING("Checking");

    private final String type;
    private AccountType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }


}
