package com.test.kata.rest.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceValidatorTest {

    private ResourceValidator resourceValidator;

    @Before
    public void setup(){
        resourceValidator = new ResourceValidator();
    }

    @Test
    public void testIsItemValidCapitalLetterIsAllowed() throws Exception {

        ItemResource mockItemResource = mock(ItemResource.class);
        when(mockItemResource.getStockKeepingUnit()).thenReturn('A');
        when(mockItemResource.getPriceInPence()).thenReturn(10L);

        boolean isValidItem = resourceValidator.isItemValid(mockItemResource);

        Assert.assertTrue(isValidItem);
    }

    @Test
    public void testIsItemValidUpperLimitIsAllowed() throws Exception {

        ItemResource mockItemResource = mock(ItemResource.class);
        when(mockItemResource.getStockKeepingUnit()).thenReturn('Z');
        when(mockItemResource.getPriceInPence()).thenReturn(10L);

        boolean isValidItem = resourceValidator.isItemValid(mockItemResource);

        Assert.assertTrue(isValidItem);
    }

    @Test
    public void testIsItemValidNonCapitalFails() throws Exception {

        ItemResource mockItemResource = mock(ItemResource.class);
        when(mockItemResource.getStockKeepingUnit()).thenReturn('a');
        when(mockItemResource.getPriceInPence()).thenReturn(10L);

        boolean isValidItem = resourceValidator.isItemValid(mockItemResource);

        Assert.assertFalse(isValidItem);
    }

    @Test
    public void testIsItemValidNegativeNumbersFail() throws Exception {

        ItemResource mockItemResource = mock(ItemResource.class);
        when(mockItemResource.getStockKeepingUnit()).thenReturn('B');
        when(mockItemResource.getPriceInPence()).thenReturn(-100L);

        boolean isValidItem = resourceValidator.isItemValid(mockItemResource);

        Assert.assertFalse(isValidItem);
    }

    @Test
    public void testIsItemValidItemsCanBeGivenAwayForFree() throws Exception {

        ItemResource mockItemResource = mock(ItemResource.class);
        when(mockItemResource.getStockKeepingUnit()).thenReturn('B');
        when(mockItemResource.getPriceInPence()).thenReturn(0L);

        boolean isValidItem = resourceValidator.isItemValid(mockItemResource);

        Assert.assertTrue(isValidItem);
    }

    @Test
    public void testIsOfferValid() throws Exception {

        OfferResource mockOfferResource = mock(OfferResource.class);
        when(mockOfferResource.getItemStockKeepingUnit()).thenReturn('G');
        when(mockOfferResource.getPriceInPence()).thenReturn(200L);
        when(mockOfferResource.getNumberOfUnits()).thenReturn(2);

        boolean isValidItem = resourceValidator.isOfferValid(mockOfferResource);

        Assert.assertTrue(isValidItem);
    }

    @Test
    public void testIsOfferValidFailsNotEnoughUnits() throws Exception {

        OfferResource mockOfferResource = mock(OfferResource.class);
        when(mockOfferResource.getItemStockKeepingUnit()).thenReturn('G');
        when(mockOfferResource.getPriceInPence()).thenReturn(200L);
        when(mockOfferResource.getNumberOfUnits()).thenReturn(1);

        boolean isValidItem = resourceValidator.isOfferValid(mockOfferResource);

        Assert.assertFalse(isValidItem);
    }

    @Test
    public void testIsOfferValidFailsSkuOutOfRange() throws Exception {

        OfferResource mockOfferResource = mock(OfferResource.class);
        when(mockOfferResource.getItemStockKeepingUnit()).thenReturn('#');
        when(mockOfferResource.getPriceInPence()).thenReturn(200L);
        when(mockOfferResource.getNumberOfUnits()).thenReturn(5);

        boolean isValidItem = resourceValidator.isOfferValid(mockOfferResource);

        Assert.assertFalse(isValidItem);
    }
}