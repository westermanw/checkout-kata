package com.test.kata.item;

public class Item {

    private char stockKeepingUnit;
    private long priceInPence;

    public Item (char stockKeepingUnit, long priceInPence){
        this.stockKeepingUnit = stockKeepingUnit;
        this.priceInPence = priceInPence;
    }

    public long getPriceInPence(){
        return priceInPence;
    }

    public char getStockKeepingUnit(){
        return stockKeepingUnit;
    }

    @Override
    public boolean equals(Object arg0){
        Item comparingItem = (Item)arg0;
        return this.stockKeepingUnit == comparingItem.getStockKeepingUnit();
    }

    @Override
    public int hashCode(){
        return stockKeepingUnit;
    }

}
