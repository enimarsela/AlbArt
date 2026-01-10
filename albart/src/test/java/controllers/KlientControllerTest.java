package controllers;

import dtos.KlientDto;
import dtos.UserDto;
import models.Klient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.KlientService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KlientControllerTest {

    @Mock
    private KlientService klientService;

    @InjectMocks
    private KlientController controller;

    private Klient klient;
    private KlientDto dto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        klient = new Klient();
        klient.setKlientId(1L);
        dto = new KlientDto();
        userDto = new UserDto();
    }

    @Test
    void testRegister() {
        when(klientService.register(dto)).thenReturn(klient);
        ResponseEntity<Klient> response = controller.register(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(klient, response.getBody());
    }

    @Test
    void testLogin() {
        when(klientService.login(userDto)).thenReturn(klient);
        ResponseEntity<Klient> response = controller.login(userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(klient, response.getBody());
    }

    @Test
    void testGetAll() {
        when(klientService.getAll()).thenReturn(Arrays.asList(klient));
        ResponseEntity<List<Klient>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetById() {
        when(klientService.getById(1L)).thenReturn(klient);
        ResponseEntity<Klient> response = controller.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(klient, response.getBody());
    }

    @Test
    void testUpdate() {
        when(klientService.update(1L, dto)).thenReturn(klient);
        ResponseEntity<Klient> response = controller.update(1L, dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(klient, response.getBody());
    }

    @Test
    void testDelete_Success() {
        when(klientService.delete(1L)).thenReturn(true);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Klient u fshi me sukses!", response.getBody());
    }

    @Test
    void testDelete_NotFound() {
        when(klientService.delete(1L)).thenReturn(false);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Klient nuk u gjet!", response.getBody());
    }
}
