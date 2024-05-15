package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.domains.dtos.BorrowingRecord;
import com.example.LibraryManagementSystem.services.BorrowingRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService)
    {
        this.borrowingRecordService = borrowingRecordService;
    }
    @GetMapping("/borrow/getAll")
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(borrowingRecordService.findAll());
    }

    @GetMapping("/return/{book_id}/patron/{patron_id}")
    public ResponseEntity<?> findById(@PathVariable long book_id,@PathVariable long patron_id)
    {
        return ResponseEntity.ok(borrowingRecordService.findById(book_id,patron_id));
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> insert(@RequestBody BorrowingRecord borrowingRecord,@PathVariable long bookId,@PathVariable long patronId)
    {
        return ResponseEntity.ok(borrowingRecordService.insert(borrowingRecord,bookId,patronId));
    }
}
