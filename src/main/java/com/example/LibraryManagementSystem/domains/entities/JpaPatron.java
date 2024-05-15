package com.example.LibraryManagementSystem.domains.entities;

import com.example.LibraryManagementSystem.domains.dtos.BorrowingRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patrons")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JpaPatron extends JpaBase{
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "patron")
    private List<JpaBorrowingRecord> borrowings;
}
