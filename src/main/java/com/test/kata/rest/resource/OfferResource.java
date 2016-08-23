package com.test.kata.rest.resource;


public class OfferResource {

    private char itemStockKeepingUnit;
    private int numberOfUnits;
    private long priceInPence;

    public OfferResource(){}

    public char getItemStockKeepingUnit() {
        return itemStockKeepingUnit;
    }

    public void setItemStockKeepingUnit(char itemStockKeepingUnit) {
        this.itemStockKeepingUnit = itemStockKeepingUnit;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public long getPriceInPence() {
        return priceInPence;
    }

    public void setPriceInPence(long priceInPence) {
        this.priceInPence = priceInPence;
    }
}
