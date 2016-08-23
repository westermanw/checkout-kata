package com.test.kata.rest;

import com.test.kata.item.ItemRepository;
import com.test.kata.rest.resource.ItemResource;
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
public class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemRepository mockItemRepository;

    @Mock
    private ResourceValidator mockResourceValidator;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(itemController).build();
    }


    @Test
    public void testAddingValidItemReturnsCorrectResponse() throws Exception {
        //Given that the incoming item is valid
        when(mockResourceValidator.isItemValid(any(ItemResource.class))).thenReturn(true);

        //Return created status
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"stockKeepingUnit\":\"A\", \"priceInPence\":5000}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddingInvalidItemReturnsBadRequest() throws Exception {
        //Given that the incoming item is not valid
        when(mockResourceValidator.isItemValid(any(ItemResource.class))).thenReturn(false);

        //Return bad request status
        mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"stockKeepingUnit\":\"A\", \"priceInPence\":-100}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemoveAllItemsFromStockList() throws Exception {
        mockMvc.perform(delete("/item"))
                .andExpect(status().isOk());

        verify(mockItemRepository, times(1)).clearAllItems();
    }

}