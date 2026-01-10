package controllers;

import dtos.ArtikullCartDto;
import models.ArtikullCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.ArtikullCartService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtikullCartControllerTest {

    @Mock
    private ArtikullCartService service;

    @InjectMocks
    private ArtikullCartController controller;

    private ArtikullCart cartItem;
    private ArtikullCartDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cartItem = new ArtikullCart();
        cartItem.setArtikullId(1L);

        dto = new ArtikullCartDto();
    }

    @Test
    void testGetById() {
        when(service.getById(1L)).thenReturn(cartItem);

        ResponseEntity<ArtikullCart> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
        verify(service).getById(1L);
    }

    @Test
    void testAddToCart() {
        when(service.create(dto)).thenReturn(cartItem);

        ResponseEntity<ArtikullCart> response = controller.addToCart(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
        verify(service).create(dto);
    }

    @Test
    void testUpdate() {
        when(service.update(1L, dto)).thenReturn(cartItem);

        ResponseEntity<ArtikullCart> response = controller.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
        verify(service).update(1L, dto);
    }

    @Test
    void testRemoveFromCart_Success() {
        when(service.delete(1L)).thenReturn(true);

        ResponseEntity<String> response = controller.removeFromCart(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Removed!", response.getBody());
        verify(service).delete(1L);
    }

    @Test
    void testRemoveFromCart_NotFound() {
        when(service.delete(1L)).thenReturn(false);

        ResponseEntity<String> response = controller.removeFromCart(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found!", response.getBody());
        verify(service).delete(1L);
    }

    @Test
    void testGetCart() {
        List<ArtikullCart> list = Arrays.asList(cartItem);
        when(service.getCartByKlient(1L)).thenReturn(list);

        ResponseEntity<List<ArtikullCart>> response = controller.getCart(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(service).getCartByKlient(1L);
    }

    @Test
    void testClearCart() {
        ResponseEntity<String> response = controller.clearCart(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shopping Cart cleared!", response.getBody());
        verify(service).clearCart(1L);
    }

    @Test
    void testCalculateTotal() {
        when(service.calculateCartTotal(1L, "normal")).thenReturn(100.0);

        ResponseEntity<Double> response = controller.calculateTotal(1L, "normal");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody());
        verify(service).calculateCartTotal(1L, "normal");
    }
}
