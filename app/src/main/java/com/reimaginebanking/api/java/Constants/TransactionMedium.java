package com.reimaginebanking.api.java.Constants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hxp347 on 5/6/15.
 */
public enum TransactionMedium {
    @SerializedName("balance")
    BALANCE("balance"),
    @SerializedName("rewards")
    REWARDS("rewards");

    private final String medium;

    private TransactionMedium(String medium){
        this.medium = medium;
    }

    @Override
    public String toString(){
        return medium;
    }
}
