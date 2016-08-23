package com.test.kata.rest;

import com.test.kata.offer.OfferRepository;
import com.test.kata.rest.resource.OfferResource;
import com.test.kata.rest.resource.ResourceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class OfferControllerTest {

    @InjectMocks
    private OfferController offerController;

    @Mock
    private OfferRepository mockOfferRepository;

    @Mock
    private ResourceValidator mockResourceValidator;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(offerController).build();
    }
    
    @Test
    public void testAddingValidOfferReturnsCorrectResponse() throws Exception {
        //Given that the incoming offer is valid
        when(mockResourceValidator.isOfferValid(any(OfferResource.class))).thenReturn(true);

        //Return created status
        mockMvc.perform(post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemStockKeepingUnit\":\"A\", \"numberOfUnits\":3, \"priceInPence\":5000}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddingInvalidOfferReturnsBadRequest() throws Exception {
        //Given that the incoming offer is not valid
        when(mockResourceValidator.isOfferValid(any(OfferResource.class))).thenReturn(false);

        //Return bad request status
        mockMvc.perform(post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemStockKeepingUnit\":\"A\", \"numberOfUnits\":3, \"priceInPence\":-100}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemoveAllOffersFromStockList() throws Exception {
        mockMvc.perform(delete("/offer"))
                .andExpect(status().isOk());

        verify(mockOfferRepository, times(1)).clearAllOffers();
    }
}