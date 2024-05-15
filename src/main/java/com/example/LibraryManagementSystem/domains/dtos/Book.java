package com.example.LibraryManagementSystem.domains.dtos;

import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Book {
    private Long id;
    @NotNull(message = "the title is required")
    private String title;
    @NotNull(message = "the author is required")

    private String author;
    @NotNull(message = "the publication year is required")
    private Long publication_year;
    @Column(unique = true)
    private int isbn;
}
