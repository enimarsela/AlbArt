package controllers;

import dtos.PorosiDto;
import models.Porosi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.PorosiService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PorosiControllerTest {

    @Mock
    private PorosiService service;

    @InjectMocks
    private PorosiController controller;

    private Porosi porosi;
    private PorosiDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Shembull Porosi
        porosi = new Porosi();
        porosi.setPorosiId(1L);
        // Vendos fusha të tjera nëse ke nevojë

        // Shembull PorosiDto
        dto = new PorosiDto();
        // Vendos fusha të tjera sipas dto-së tënde
    }

    // --------------------- GET ALL ---------------------
    @Test
    void testGetAll() {
        List<Porosi> list = Arrays.asList(porosi);
        when(service.getAll()).thenReturn(list);

        ResponseEntity<List<Porosi>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(service, times(1)).getAll();
    }

    // --------------------- GET BY ID ---------------------
    @Test
    void testGetById() {
        when(service.getById(1L)).thenReturn(porosi);

        ResponseEntity<Porosi> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(porosi, response.getBody());
        verify(service, times(1)).getById(1L);
    }

    // --------------------- CREATE ---------------------
    @Test
    void testCreate() {
        when(service.create(dto)).thenReturn(porosi);

        ResponseEntity<Porosi> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(porosi, response.getBody());
        verify(service, times(1)).create(dto);
    }

    // --------------------- UPDATE ---------------------
    @Test
    void testUpdate() {
        when(service.update(1L, dto)).thenReturn(porosi);

        ResponseEntity<Porosi> response = controller.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(porosi, response.getBody());
        verify(service, times(1)).update(1L, dto);
    }

    // --------------------- DELETE SUCCESS ---------------------
    @Test
    void testDelete_Success() {
        when(service.delete(1L)).thenReturn(true);

        ResponseEntity<String> response = controller.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted!", response.getBody());
        verify(service, times(1)).delete(1L);
    }

    // --------------------- DELETE NOT FOUND ---------------------
    @Test
    void testDelete_NotFound() {
        when(service.delete(1L)).thenReturn(false);

        ResponseEntity<String> response = controller.delete(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found!", response.getBody());
        verify(service, times(1)).delete(1L);
    }
}
