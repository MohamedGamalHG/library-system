package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.domains.entities.JpaBorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<JpaBorrowingRecord,Long> {
    Optional<JpaBorrowingRecord> findByBookIdAndPatronId(long book_id, long patron_id);
}
