package com.test.kata.rest;

import com.test.kata.item.Item;
import com.test.kata.item.ItemRepository;
import com.test.kata.rest.resource.ItemResource;
import com.test.kata.rest.resource.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ResourceValidator resourceValidator;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<?> addItem(@RequestBody ItemResource itemResource){

        boolean isValidItem = resourceValidator.isItemValid(itemResource);

        HttpStatus requestStatus;
        String jsonResponse;

        if(isValidItem) {
            Item item = new Item(itemResource.getStockKeepingUnit(), itemResource.getPriceInPence());
            itemRepository.storeItem(item);

            requestStatus = HttpStatus.CREATED;
            jsonResponse = "{\"Response\":\"Item has been successfully added to store\"}";
        }else{
            requestStatus = HttpStatus.BAD_REQUEST;
            jsonResponse = "{\"Response\":\"Cannot create item; item is invalid\"}";
        }

        return new ResponseEntity<>(jsonResponse, requestStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> removeAllItemsFromStockList(){

        itemRepository.clearAllItems();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
