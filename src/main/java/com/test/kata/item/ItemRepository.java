package com.test.kata.item;


import java.util.HashSet;
import java.util.Optional;

/**
 * As long as the item storage implements this interface it doesn't matter how the items are stored
 */
public interface ItemRepository {

    void storeItem(Item item);

    void clearAllItems();

    Optional<Item> getItemFromSku(char itemSku);

    HashSet<Item> getAllItems();
}
