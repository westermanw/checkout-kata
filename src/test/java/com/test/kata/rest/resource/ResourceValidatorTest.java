package com.test.kata.rest.resource;

import com.test.kata.item.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
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

}