package com.test.kata.offer;

public class Offer {

    private char itemSku;
    private int multiplicity;
    private long priceInPence;

    public Offer(char itemSku, int multiplicity, long priceInPence){
        this.itemSku = itemSku;
        this.multiplicity = multiplicity;
        this.priceInPence = priceInPence;
    }


    public int getMultiplicity() {
        return multiplicity;
    }

    public long getPriceInPence() {
        return priceInPence;
    }

    public char getItemSku() {
        return itemSku;
    }

    /**
     * Offers are stored in set so need to override equals and hashcode so that multiple offers cannot be present
     * on a single item.
     */
    @Override
    public boolean equals(Object arg0){
        Offer comparingItem = (Offer)arg0;
        return this.itemSku == comparingItem.itemSku;
    }

    @Override
    public int hashCode(){
        return itemSku;
    }

}
