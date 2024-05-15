package com.example.LibraryManagementSystem.domains.dtos;

import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RetriveBorrowingRecord {
    private Long id;
    private Book book;
    private Patron patron;
    private Date return_date;
    private Date borrowing_date;
}
