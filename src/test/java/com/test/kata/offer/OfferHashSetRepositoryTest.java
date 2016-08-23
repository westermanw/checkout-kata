package com.test.kata.offer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
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

    @Test
    public void testOfferOverwritesPreviousOffer() throws Exception {
        Offer offerA = new Offer('A', 2, 3L);
        Offer offerATwo = new Offer('A', 2, 100L);

        offerHashSetRepository.storeOffer(offerA);
        offerHashSetRepository.storeOffer(offerATwo);
        Optional<Offer> optionalOffer = offerHashSetRepository.getOfferFromSku('A');

        Assert.assertTrue(optionalOffer.isPresent());
        Assert.assertEquals(offerATwo, optionalOffer.get());
    }

}