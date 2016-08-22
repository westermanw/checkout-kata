package com.test.kata.offer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferHashSetRepositoryTest {

    private OfferHashSetRepository offerHashSetRepository;

    @Before
    public void setup(){
        offerHashSetRepository = new OfferHashSetRepository();
    }

    @Test
    public void getOfferFromSku() throws Exception {
        Offer mockOfferT = mock(Offer.class);
        when(mockOfferT.getItemSku()).thenReturn('t');

        offerHashSetRepository.storeOffer(mockOfferT);
        Optional<Offer> optionalOffer = offerHashSetRepository.getOfferFromSku('t');

        Assert.assertTrue(optionalOffer.isPresent());
        Assert.assertEquals(mockOfferT, optionalOffer.get());
    }

}