package com.example.LibraryManagementSystem.domains.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BorrowingRecord {
    private Long id;
    private long book_id;
    private long patron_id;
    private Date return_date;
    private Date borrowing_date;
}
