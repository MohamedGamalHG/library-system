package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<JpaPatron,Long> {
}
