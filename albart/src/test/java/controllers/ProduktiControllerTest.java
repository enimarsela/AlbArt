package controllers;

import dtos.ProduktiDto;
import models.Produkti;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.ProduktiService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProduktiControllerTest {

    @Mock
    private ProduktiService produktiService;

    @InjectMocks
    private ProduktiController controller;

    private Produkti produkti;
    private ProduktiDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produkti = new Produkti();
        produkti.setProduktId(1L);
        dto = new ProduktiDto();
    }

    @Test
    void testGetAllProdukt() {
        when(produktiService.getAllProdukti()).thenReturn(Arrays.asList(produkti));
        ResponseEntity<List<Produkti>> response = controller.getAllProdukt();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetAllProduktibyKategoria() {
        when(produktiService.getProduktiByKategoria(1L)).thenReturn(Arrays.asList(produkti));
        ResponseEntity<List<Produkti>> response = controller.getAllProduktibyKategoria(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetProduktibyId() {
        when(produktiService.getProduktiById(1L)).thenReturn(produkti);
        ResponseEntity<Produkti> response = controller.getProduktibyId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produkti, response.getBody());
    }

    @Test
    void testCreateProdukt() {
        when(produktiService.createProdukt(dto)).thenReturn(produkti);
        ResponseEntity<Produkti> response = controller.createProdukt(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produkti, response.getBody());
    }

    @Test
    void testUpdateProdukti() {
        when(produktiService.updateProdukti(dto, 1L)).thenReturn(produkti);
        ResponseEntity<Produkti> response = controller.updateProdukti(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produkti, response.getBody());
    }

    @Test
    void testDeleteProdukti_Success() {
        when(produktiService.deleteProdukti(1L)).thenReturn(true);
        ResponseEntity<String> response = controller.deleteProdukti(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produkti deleted successfully!", response.getBody());
    }

    @Test
    void testDeleteProdukti_NotFound() {
        when(produktiService.deleteProdukti(1L)).thenReturn(false);
        ResponseEntity<String> response = controller.deleteProdukti(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Produkti not found!", response.getBody());
    }
}
