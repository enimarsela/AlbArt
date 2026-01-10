package controllers;

import dtos.PagesaDto;
import models.Pagesa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.PagesaService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagesaControllerTest {

    @Mock
    private PagesaService service;

    @InjectMocks
    private PagesaController controller;

    private Pagesa pagesa;
    private PagesaDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagesa = new Pagesa();
        pagesa.setPagesaId(1L);
        dto = new PagesaDto();
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(Arrays.asList(pagesa));
        ResponseEntity<List<Pagesa>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetById() {
        when(service.getById(1L)).thenReturn(pagesa);
        ResponseEntity<Pagesa> response = controller.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagesa, response.getBody());
    }

    @Test
    void testCreate() {
        when(service.create(dto)).thenReturn(pagesa);
        ResponseEntity<Pagesa> response = controller.create(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pagesa, response.getBody());
    }

    @Test
    void testUpdate() {
        when(service.update(1L, dto)).thenReturn(pagesa);
        ResponseEntity<Pagesa> response = controller.update(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagesa, response.getBody());
    }

    @Test
    void testDelete_Success() {
        when(service.delete(1L)).thenReturn(true);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted!", response.getBody());
    }

    @Test
    void testDelete_NotFound() {
        when(service.delete(1L)).thenReturn(false);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found!", response.getBody());
    }
}
