package com.test.kata.rest;

import com.test.kata.basket.BasketService;
import com.test.kata.item.Item;
import com.test.kata.item.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BasketControllerTest {

    @InjectMocks
    private BasketController basketController;

    @Mock
    private BasketService mockBasketService;

    @Mock
    private ItemRepository mockItemRepository;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = standaloneSetup(basketController).build();
    }
    
    
    @Test
    public void getTotal() throws Exception {
        when(mockBasketService.getTotalInPence()).thenReturn(10L);

        mockMvc.perform(get("/basket/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"total\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddItemToBasketNonExistentItemReturnsBadReq() throws Exception {
        when(mockItemRepository.getItemFromSku('C')).thenReturn(Optional.empty());

        mockMvc.perform(post("/basket/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"stockKeepingUnit\":\"C\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddItemToBasketIsValidReturnsOk() throws Exception {
        Item mockItem = mock(Item.class);
        Optional<Item> optionalItemMock = Optional.of(mockItem);

        when(mockItemRepository.getItemFromSku('G')).thenReturn(optionalItemMock);

        mockMvc.perform(post("/basket/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"stockKeepingUnit\":\"G\"}"))
                .andExpect(status().isOk());

        verify(mockBasketService, times(1)).addItem(optionalItemMock.get());
    }

    @Test
    public void deleteAllItems() throws Exception {
        mockMvc.perform(delete("/basket"))
                .andExpect(status().isOk());

        verify(mockBasketService, times(1)).clear();
    }

}