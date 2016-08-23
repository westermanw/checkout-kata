package com.test.kata.rest;
import com.test.kata.basket.BasketService;
import com.test.kata.item.Item;
import com.test.kata.item.ItemRepository;
import com.test.kata.rest.resource.StockKeepingUnitResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/total", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> getTotal() {

        long totalInPence = basketService.getTotalInPence();
        String jsonTotal = "{\"total\":" + totalInPence + "}";

        return new ResponseEntity<>(jsonTotal, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<?> addItemToBasket(@RequestBody StockKeepingUnitResource stockKeepingUnitResource) {

        char sku = stockKeepingUnitResource.getStockKeepingUnit();
        Optional<Item> optionalItem = itemRepository.getItemFromSku(sku);

        HttpStatus requestStatus;
        String jsonResponse;

        if(optionalItem.isPresent()){
            basketService.addItem(optionalItem.get());

            jsonResponse = "{\"response\":\"" + sku + " has been added to your basket\"}";
            requestStatus = HttpStatus.OK;

        }else{
            jsonResponse = "{\"response\":\"ERROR... " + sku
                    + " cannot be added to the basket, it does not exist in our stock list!\"}";
            requestStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(jsonResponse, requestStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAllItems() {

        basketService.clear();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
