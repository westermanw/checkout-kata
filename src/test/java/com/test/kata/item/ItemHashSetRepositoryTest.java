package com.test.kata.item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemHashSetRepositoryTest {

    private ItemHashSetRepository itemHashSetRepository;

    @Before
    public void setup(){
        itemHashSetRepository = new ItemHashSetRepository();
    }

    @Test
    public void testStoreItem() throws Exception {
        Item mockItemA = mock(Item.class);

        when(mockItemA.getStockKeepingUnit()).thenReturn('a');

        itemHashSetRepository.storeItem(mockItemA);

        Assert.assertEquals(1, itemHashSetRepository.getAllItems().size());
        Assert.assertTrue(itemHashSetRepository.getAllItems().contains(mockItemA));
    }

    @Test
    public void testDuplicateItemIsNotStoredTwice() throws Exception {
        Item mockItemA = mock(Item.class);

        when(mockItemA.getStockKeepingUnit()).thenReturn('a');

        itemHashSetRepository.storeItem(mockItemA);
        itemHashSetRepository.storeItem(mockItemA);

        Assert.assertEquals(1, itemHashSetRepository.getAllItems().size());
        Assert.assertTrue(itemHashSetRepository.getAllItems().contains(mockItemA));
    }

    @Test
    public void testGetItemFromSku() throws Exception {
        Item mockItemZ = mock(Item.class);
        when(mockItemZ.getStockKeepingUnit()).thenReturn('z');

        itemHashSetRepository.storeItem(mockItemZ);
        Optional<Item> optionalItem = itemHashSetRepository.getItemFromSku('z');

        Assert.assertTrue(optionalItem.isPresent());
        Assert.assertEquals(mockItemZ, optionalItem.get());
    }
}