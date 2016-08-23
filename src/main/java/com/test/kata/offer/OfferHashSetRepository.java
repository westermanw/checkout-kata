package com.test.kata.offer;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Optional;

@Component
public class OfferHashSetRepository implements OfferRepository{


    private HashSet<Offer> offerSet;

    public OfferHashSetRepository(){
        this.offerSet = new HashSet<>();
    }

    @Override
    public void clearAllOffers() {
        offerSet.clear();
    }

    @Override
    public Optional<Offer> getOfferFromSku(char itemSku) {
        Optional<Offer> optionalOffer = Optional.empty();

        for(Offer offer : offerSet){
            if(offer.getItemSku() == itemSku){
                optionalOffer = Optional.of(offer);
            }
        }

        return optionalOffer;
    }

    @Override
    public void storeOffer(Offer offer) {
        if(offerSet.contains(offer)){
            offerSet.remove(offer);
        }
        offerSet.add(offer);
    }

    @Override
    public HashSet<Offer> getAllOffers() {
        return offerSet;
    }
}
