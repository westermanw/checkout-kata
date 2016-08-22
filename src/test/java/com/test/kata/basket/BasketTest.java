package com.test.kata.basket;

import com.test.kata.item.Item;
import com.test.kata.offer.Offer;
import com.test.kata.offer.OfferRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketTest {

    @Mock
    OfferRepository offerRepository;

    @InjectMocks
    private BasketService basketService;


    @Test
    public void testGetTotalInPenceAllInOffer() throws Exception {
        Offer mockOffer = mock(Offer.class);
        when(mockOffer.getMultiplicity()).thenReturn(3);
        when(mockOffer.getPriceInPence()).thenReturn(200L);

        Item mockItem = mock(Item.class);
        when(mockItem.getStockKeepingUnit()).thenReturn('a');

        Optional<Offer> optionalOffer = Optional.of(mockOffer);
        when(offerRepository.getOfferFromSku('a')).thenReturn(optionalOffer);


        basketService.addItem(mockItem);
        basketService.addItem(mockItem);
        basketService.addItem(mockItem);

        Assert.assertEquals(200L, basketService.getTotalInPence());
    }

    @Test
    public void testGetTotalInPenceSomeInOffer() throws Exception {
        Offer mockOffer = mock(Offer.class);
        when(mockOffer.getMultiplicity()).thenReturn(3);
        when(mockOffer.getPriceInPence()).thenReturn(200L);

        Item mockItem = mock(Item.class);
        when(mockItem.getStockKeepingUnit()).thenReturn('a');
        when(mockItem.getPriceInPence()).thenReturn(100L);

        Optional<Offer> optionalOffer = Optional.of(mockOffer);
        when(offerRepository.getOfferFromSku('a')).thenReturn(optionalOffer);


        basketService.addItem(mockItem);
        basketService.addItem(mockItem);
        basketService.addItem(mockItem);
        basketService.addItem(mockItem);

        Assert.assertEquals(300L, basketService.getTotalInPence());
    }

    @Test
    public void testGetTotalInPenceOfferItemsNotAddedContinuous() throws Exception {
        Offer mockOffer = mock(Offer.class);
        when(mockOffer.getMultiplicity()).thenReturn(2);
        when(mockOffer.getPriceInPence()).thenReturn(35L);

        Item mockItem = mock(Item.class);
        when(mockItem.getStockKeepingUnit()).thenReturn('a');
        when(mockItem.getPriceInPence()).thenReturn(100L);

        Item mockItemB = mock(Item.class);
        when(mockItemB.getStockKeepingUnit()).thenReturn('b');
        when(mockItemB.getPriceInPence()).thenReturn(10L);

        Optional<Offer> optionalOffer = Optional.of(mockOffer);
        when(offerRepository.getOfferFromSku('a')).thenReturn(optionalOffer);
        when(offerRepository.getOfferFromSku('b')).thenReturn(Optional.empty());

        basketService.addItem(mockItem);
        basketService.addItem(mockItemB);
        basketService.addItem(mockItem);

        Assert.assertEquals(45L, basketService.getTotalInPence());
    }

    @Test
    public void testGetTotalInPenceDifferentItemsNoOffers() throws Exception {
        Item mockItemA = mock(Item.class);
        when(mockItemA.getStockKeepingUnit()).thenReturn('a');
        when(mockItemA.getPriceInPence()).thenReturn(100L);

        Item mockItemB = mock(Item.class);
        when(mockItemB.getStockKeepingUnit()).thenReturn('b');
        when(mockItemB.getPriceInPence()).thenReturn(10L);

        when(offerRepository.getOfferFromSku('a')).thenReturn(Optional.empty());
        when(offerRepository.getOfferFromSku('b')).thenReturn(Optional.empty());

        basketService.addItem(mockItemA);
        basketService.addItem(mockItemB);
        basketService.addItem(mockItemB);

        Assert.assertEquals(120L, basketService.getTotalInPence());
    }

    @Test
    public void testAddItemIncrementsHashMapValue() throws Exception {
        Item mockItemA = mock(Item.class);
        Item mockItemB = mock(Item.class);

        basketService.addItem(mockItemA);
        basketService.addItem(mockItemA);

        Assert.assertEquals(2, basketService.getItemCount(mockItemA));
        Assert.assertEquals(0, basketService.getItemCount(mockItemB));
    }

}