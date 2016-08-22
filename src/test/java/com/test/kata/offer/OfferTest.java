package com.test.kata.offer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Will on 22/08/2016.
 */
public class OfferTest {
    @Test
    public void testEquals() throws Exception {
        Offer offerA = new Offer('b', 5, 150);
        Offer offerB = new Offer('b', 10, 1000);

        Assert.assertTrue(offerA.equals(offerB));
    }

    @Test
    public void testNegativeEquals() throws Exception {
        Offer offerA = new Offer('b', 5, 150);
        Offer offerB = new Offer('c', 10, 1000);

        Assert.assertFalse(offerA.equals(offerB));
    }

    @Test
    public void testHashCode() throws Exception {
        Offer offerA = new Offer('b', 5, 150);
        Offer offerB = new Offer('b', 10, 1000);

        Assert.assertTrue(offerA.hashCode() == offerB.hashCode());
    }

}