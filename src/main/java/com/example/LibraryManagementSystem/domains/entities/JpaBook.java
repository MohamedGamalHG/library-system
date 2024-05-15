package com.example.LibraryManagementSystem.domains.entities;

import com.example.LibraryManagementSystem.domains.dtos.BorrowingRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaBook extends JpaBase {

    private String title;
    private String author;
    private Long publication_year;
    private int isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "borrowing_record",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "patron_id"))
    private List<JpaBorrowingRecord> borrowedBy;
}
