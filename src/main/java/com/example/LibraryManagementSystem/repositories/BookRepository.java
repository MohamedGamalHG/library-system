package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<JpaBook,Long> {

    Long countByIsbn(int isbn);
}
