package controllers;

import dtos.KategoriaDto;
import models.Kategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.KategoriaService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KategoriaControllerTest {

    @Mock
    private KategoriaService service;

    @InjectMocks
    private KategoriaController controller;

    private Kategoria kategoria;
    private KategoriaDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kategoria = new Kategoria();
        kategoria.setKategoriId(1L);
        dto = new KategoriaDto();
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(Arrays.asList(kategoria));
        ResponseEntity<List<Kategoria>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetById() {
        when(service.getById(1L)).thenReturn(kategoria);
        ResponseEntity<Kategoria> response = controller.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(kategoria, response.getBody());
    }

    @Test
    void testCreate() {
        when(service.create(dto)).thenReturn(kategoria);
        ResponseEntity<Kategoria> response = controller.create(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(kategoria, response.getBody());
    }

    @Test
    void testUpdate() {
        when(service.update(1L, dto)).thenReturn(kategoria);
        ResponseEntity<Kategoria> response = controller.update(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(kategoria, response.getBody());
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
