package com.test.kata.item;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    /**
     * Items should equal each other as long as the sku is identical. Shouldn't be possible to have
     * two items with the same SKU in any set.
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {
        Item itemOne = new Item('a', 300);
        Item itemTwo = new Item('a', 500);

        Assert.assertTrue(itemOne.equals(itemTwo));
    }

    @Test
    public void testNegativeEquals() throws Exception {
        Item itemOne = new Item('a', 300);
        Item itemTwo = new Item('v', 500);

        Assert.assertFalse(itemOne.equals(itemTwo));
    }

    /**
     * Make sure Items with the same SKU hash to the same value
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {
        Item itemOne = new Item('c', 1);
        Item itemTwo = new Item('c', 1000);

        Assert.assertTrue(itemOne.hashCode() == itemTwo.hashCode());
    }

}