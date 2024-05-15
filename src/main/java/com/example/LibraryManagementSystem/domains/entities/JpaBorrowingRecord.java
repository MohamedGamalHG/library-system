package com.example.LibraryManagementSystem.domains.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "borrowing_record")
@Setter
@Getter
public class JpaBorrowingRecord extends JpaBase{
//    private long BookId;
//    private long PatronId;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private JpaPatron patron;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private JpaBook book;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date return_date;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date borrowing_date;
}
