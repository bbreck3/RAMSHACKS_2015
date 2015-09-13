package com.reimaginebanking.api.java.Constants;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hxp347 on 5/6/15.
 */
public enum BillStatus {
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("canceled")
    CANCELLED("canceled"),
    @SerializedName("completed")
    COMPLETED("completed"),
    @SerializedName("recurring")
    RECURRING("recurring");

    private final String status;

    private BillStatus(final String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }
}
