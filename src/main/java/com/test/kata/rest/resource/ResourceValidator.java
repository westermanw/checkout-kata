package com.test.kata.rest.resource;

import org.springframework.stereotype.Component;

@Component
public class ResourceValidator {

    public ResourceValidator(){}

    public boolean isItemValid(ItemResource itemResource){

        boolean isValid = false;

        long priceInPence = itemResource.getPriceInPence();
        char sku = itemResource.getStockKeepingUnit();

        if(isPriceValid(priceInPence) && isStockKeepingUnitCharValid(sku)){
            isValid = true;
        }

        return isValid;
    }


    public boolean isOfferValid(OfferResource offerResource){

        boolean isValid = false;

        long priceInPence = offerResource.getPriceInPence();
        int numberOfUnits = offerResource.getNumberOfUnits();
        char sku = offerResource.getItemStockKeepingUnit();

        if(isPriceValid(priceInPence) && isStockKeepingUnitCharValid(sku) && isOfferUnitsValid(numberOfUnits)){
            isValid = true;
        }

        return isValid;
    }

    private boolean isStockKeepingUnitCharValid(char sku){
        return (sku >= 65 && sku <= 90);
    }

    private boolean isPriceValid(long price){
        return (price >= 0);
    }

    private boolean isOfferUnitsValid(int numberOfUnits){
        return (numberOfUnits >= 2);
    }


}
