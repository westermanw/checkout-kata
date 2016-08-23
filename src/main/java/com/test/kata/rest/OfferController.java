package com.test.kata.rest;

import com.test.kata.offer.Offer;
import com.test.kata.offer.OfferRepository;
import com.test.kata.rest.resource.OfferResource;
import com.test.kata.rest.resource.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private ResourceValidator resourceValidator;

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<?> addOffer(@RequestBody OfferResource offerResource){

        boolean isValidOffer = resourceValidator.isOfferValid(offerResource);

        HttpStatus requestStatus;
        String jsonResponse;

        if(isValidOffer) {
            Offer offer = new Offer(offerResource.getItemStockKeepingUnit(), offerResource.getNumberOfUnits(),
                                    offerResource.getPriceInPence());

            offerRepository.storeOffer(offer);

            requestStatus = HttpStatus.CREATED;
            jsonResponse = "{\"Response\":\"Offer has been successfully added\"}";
        }else{
            requestStatus = HttpStatus.BAD_REQUEST;
            jsonResponse = "{\"Response\":\"Offer cannot be saved because the offer is not valid\"}";
        }

        return new ResponseEntity<>(jsonResponse, requestStatus);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<?> removeAllOffersFromStore(){

        offerRepository.clearAllOffers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
