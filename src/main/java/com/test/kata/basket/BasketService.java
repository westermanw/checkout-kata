package com.test.kata.basket;

import com.test.kata.item.Item;
import com.test.kata.offer.Offer;
import com.test.kata.offer.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Optional;


@Service
public class BasketService {

    private HashMap<Item, Integer> scannedItems;

    @Autowired
    OfferRepository offerRepository;

    public BasketService(){
        scannedItems = new HashMap<>();
    }

    public long getTotalInPence(){
        long total = 0;

        for (Item item: scannedItems.keySet()) {
            int itemCount = scannedItems.get(item);
            total = total + calculateItemCosts(item, itemCount);
        }

        return total;
    }

    private long calculateItemCosts(Item item, int itemCount){
        long total = 0;
        int remainder = itemCount;

        Optional<Offer> optionalOffer = offerRepository.getOfferFromSku(item.getStockKeepingUnit());

        //Use offer price if it exists and there are sufficient multiples
        if(optionalOffer.isPresent()) {
            Offer validOffer = optionalOffer.get();

            long offerPriceInPence = validOffer.getPriceInPence();
            int multiplicity = validOffer.getMultiplicity();

            int numberOfOffersSatisfied = (int) Math.floor(itemCount / multiplicity);
            total = total + numberOfOffersSatisfied * offerPriceInPence;

            remainder = itemCount % multiplicity;
        }

        //Use item price for the remainder that sit outside the multibuys
        total = total + (remainder * item.getPriceInPence());

        return total;
    }

    public void addItem(Item item){
        if(scannedItems.containsKey(item)){
            scannedItems.put(item, scannedItems.get(item) + 1);
        }else{
            scannedItems.put(item, 1);
        }
    }

    public void clear(){
        scannedItems.clear();
    }

    public int getItemCount(Item item){
        int count = 0;

        if(scannedItems.containsKey(item)){
            count = scannedItems.get(item);
        }
        return count;
    }
}
