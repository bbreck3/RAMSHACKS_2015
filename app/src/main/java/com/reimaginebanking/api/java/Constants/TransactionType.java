package com.reimaginebanking.api.java.Constants;

/**
 * Created by hxp347 on 5/6/15.
 */
public enum TransactionType {
    P2P("p2p"),
    DEPOSIT("deposit"),
    WITHDRAWAL("withdrawal");

    private final String type;

    private TransactionType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }
}
