package com.test.kata.offer;


import java.util.HashSet;
import java.util.Optional;

public interface OfferRepository {

    void clearAllOffers();

    Optional<Offer> getOfferFromSku(char itemSku);

    void storeOffer(Offer offer);

    HashSet<Offer> getAllOffers();
}
