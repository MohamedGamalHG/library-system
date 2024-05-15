package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.domains.dtos.Book;
import com.example.LibraryManagementSystem.services.BookService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;
    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(bookService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody  Book bookInsert)
    {
            Book book = bookService.insert(bookInsert);
            return ResponseEntity.ok(book);
    }
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Book bookInsert)
    {
        Book book = bookService.update(bookInsert);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public  boolean delete(@PathVariable Long id)
    {
        bookService.delete(id);
        return true;
    }
}
