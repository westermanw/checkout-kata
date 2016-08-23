package com.test.kata.item;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class ItemHashSetRepository implements ItemRepository{

    private HashSet<Item> itemSet;

    public ItemHashSetRepository(){
        this.itemSet = new HashSet<>();
    }

    @Override
    public void storeItem(Item item) {
        if(itemSet.contains(item)){
            itemSet.remove(item);
        }
        itemSet.add(item);
    }

    @Override
    public void clearAllItems() {
        itemSet.clear();
    }

    @Override
    public Optional<Item> getItemFromSku(char itemSku) {
        Optional<Item> optionalItem = Optional.empty();

        for(Item item : itemSet){
            if(item.getStockKeepingUnit() == itemSku){
                optionalItem = Optional.of(item);
            }
        }

        return optionalItem;
    }

    @Override
    public HashSet<Item> getAllItems() {
        return itemSet;
    }
}
