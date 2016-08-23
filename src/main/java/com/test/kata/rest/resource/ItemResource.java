package com.test.kata.rest.resource;

/**
 * Resource for mapping JSON coming through POST request would also be used for get requests to
 * check items kept in the store (however this is not a requirement of kata). This incoming resource would
 * normally be validated before going on to create a new Item and storing
 */
public class ItemResource {

    private char stockKeepingUnit;

    private long priceInPence;

    public ItemResource(){
    }

    public long getPriceInPence() {
        return priceInPence;
    }

    public char getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setPriceInPence(long priceInPence) {
        this.priceInPence = priceInPence;
    }

    public void setStockKeepingUnit(char stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }
}
