package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.domains.dtos.Book;
import com.example.LibraryManagementSystem.domains.dtos.Patron;
import com.example.LibraryManagementSystem.services.BookService;
import com.example.LibraryManagementSystem.services.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private PatronService patronService;
    public PatronController(PatronService patronService)
    {
        this.patronService = patronService;
    }
    @GetMapping
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(patronService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(patronService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody Patron patron)
    {
        Patron patron1 = patronService.insert(patron);
        return ResponseEntity.ok(patron1);
    }
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Patron patron)
    {
        Patron patron1 = patronService.update(patron);
        return ResponseEntity.ok(patron1);
    }

    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        patronService.delete(id);
        return true;
    }
}
