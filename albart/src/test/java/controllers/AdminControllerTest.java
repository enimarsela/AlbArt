package controllers;

import dtos.AdminDto;
import dtos.UserDto;
import models.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.AdminService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController controller;

    private Admin admin;
    private AdminDto adminDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin();
        admin.setAdminId(1L);

        adminDto = new AdminDto();
        userDto = new UserDto();
    }

    @Test
    void testTestEndpoint() {
        String result = controller.test();
        assertEquals("It works!", result);
    }

    @Test
    void testRegisterAdmin() {
        when(adminService.registerAdmin(userDto)).thenReturn(admin);
        ResponseEntity<Admin> response = controller.registerAdmin(userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
        verify(adminService).registerAdmin(userDto);
    }

    @Test
    void testLogin() {
        when(adminService.login(userDto)).thenReturn(admin);
        ResponseEntity<Admin> response = controller.login(userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
        verify(adminService).login(userDto);
    }

    @Test
    void testGetAll() {
        List<Admin> admins = Arrays.asList(admin);
        when(adminService.getAll()).thenReturn(admins);
        ResponseEntity<List<Admin>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admins, response.getBody());
        verify(adminService).getAll();
    }

    @Test
    void testGetById() {
        when(adminService.getById(1L)).thenReturn(admin);
        ResponseEntity<Admin> response = controller.getById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
        verify(adminService).getById(1L);
    }

    @Test
    void testUpdate() {
        when(adminService.update(1L, adminDto)).thenReturn(admin);
        ResponseEntity<Admin> response = controller.update(1L, adminDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
        verify(adminService).update(1L, adminDto);
    }

    @Test
    void testDelete_Success() {
        when(adminService.deleteAdmin(1L)).thenReturn(true);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admini u fshi me sukses!", response.getBody());
        verify(adminService).deleteAdmin(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(adminService.deleteAdmin(1L)).thenReturn(false);
        ResponseEntity<String> response = controller.delete(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Admini nuk u gjet!", response.getBody());
        verify(adminService).deleteAdmin(1L);
    }
}
