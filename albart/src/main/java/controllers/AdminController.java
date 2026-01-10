package controllers;

import dtos.AdminDto;
import dtos.UserDto;
import models.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit");
        return "It works!";
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody UserDto userDto) {
        System.out.println("Controller hit! DTO: " + userDto);
        Admin admin = adminService.registerAdmin(userDto);
        return ResponseEntity.ok(admin);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody UserDto userDto) {
        Admin admin = adminService.login(userDto);
        return ResponseEntity.ok(admin);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Admin>> getAll() {
        System.out.println("Test");
        return ResponseEntity.ok(adminService.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @RequestBody AdminDto adminDto) {

        return ResponseEntity.ok(adminService.update(id, adminDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = adminService.deleteAdmin(id);

        if (!deleted)
            return ResponseEntity.badRequest().body("Admini nuk u gjet!");

        return ResponseEntity.ok("Admini u fshi me sukses!");
    }
}
