package controllers;

import dtos.ArtistiDto;
import dtos.UserDto;
import models.Artisti;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.ArtistiService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtistiControllerTest {

    @Mock
    private ArtistiService artistiService;

    @InjectMocks
    private ArtistiController controller;

    private Artisti artist;
    private ArtistiDto dto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        artist = new Artisti();
        artist.setArtistId(1L);

        dto = new ArtistiDto();
        userDto = new UserDto();
    }

    @Test
    void testRegisterArtist() {
        when(artistiService.registerArtist(dto)).thenReturn(artist);

        ResponseEntity<Artisti> response = controller.registerArtist(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(artist, response.getBody());
        verify(artistiService).registerArtist(dto);
    }

    @Test
    void testLogin() {
        when(artistiService.login(userDto)).thenReturn(artist);

        ResponseEntity<Artisti> response = controller.login(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(artist, response.getBody());
        verify(artistiService).login(userDto);
    }

    @Test
    void testGetAll() {
        List<Artisti> list = Arrays.asList(artist);
        when(artistiService.getAllArtists()).thenReturn(list);

        ResponseEntity<List<Artisti>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
        verify(artistiService).getAllArtists();
    }

    @Test
    void testGetById() {
        when(artistiService.getArtistById(1L)).thenReturn(artist);

        ResponseEntity<Artisti> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(artist, response.getBody());
        verify(artistiService).getArtistById(1L);
    }

    @Test
    void testUpdate() {
        when(artistiService.updateArtist(1L, dto)).thenReturn(artist);

        ResponseEntity<Artisti> response = controller.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(artist, response.getBody());
        verify(artistiService).updateArtist(1L, dto);
    }

    @Test
    void testDelete_Success() {
        when(artistiService.deleteArtist(1L)).thenReturn(true);

        ResponseEntity<String> response = controller.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Artisti u fshi me sukses!", response.getBody());
        verify(artistiService).deleteArtist(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(artistiService.deleteArtist(1L)).thenReturn(false);

        ResponseEntity<String> response = controller.delete(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Artisti nuk u gjet!", response.getBody());
        verify(artistiService).deleteArtist(1L);
    }
}
