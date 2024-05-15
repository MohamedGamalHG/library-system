package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.domainMap.BookMapper;
import com.example.LibraryManagementSystem.domainMap.BorrowingRecordMapper;
import com.example.LibraryManagementSystem.domainMap.PatronMapper;
import com.example.LibraryManagementSystem.domains.dtos.BorrowingRecord;
import com.example.LibraryManagementSystem.domains.dtos.Patron;
import com.example.LibraryManagementSystem.domains.dtos.RetriveBorrowingRecord;
import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import com.example.LibraryManagementSystem.domains.entities.JpaBorrowingRecord;
import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import com.example.LibraryManagementSystem.exceptionHandling.RecordNotFoundException;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import com.example.LibraryManagementSystem.repositories.BorrowingRecordRepository;
import com.example.LibraryManagementSystem.repositories.PatronRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BorrowingRecordMapper borrowingRecordMapper;
    private final PatronMapper patronMapper;
    private final BookMapper bookMapper;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository,
                                  PatronRepository patronRepository,
                                  BookRepository bookRepository,
                                  BorrowingRecordMapper borrowingRecordMapper,
                                  PatronMapper patronMapper,
                                  BookMapper bookMapper)
    {

        this.borrowingRecordRepository = borrowingRecordRepository;
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
        this.borrowingRecordMapper = borrowingRecordMapper;
        this.patronMapper = patronMapper;
        this.bookMapper = bookMapper;
    }

    public List<RetriveBorrowingRecord> findAll()
    {
        List<RetriveBorrowingRecord> borrowingRecords = new ArrayList<>();
        for (JpaBorrowingRecord jpaBorrowingRecord:borrowingRecordRepository.findAll()) {

            //borrowingRecords.add(borrowingRecordMapper.convert(jpaBorrowingRecord));
            RetriveBorrowingRecord borrowingRecord = new RetriveBorrowingRecord();
            borrowingRecord.setBook(bookMapper.convert(jpaBorrowingRecord.getBook()));
            borrowingRecord.setPatron(patronMapper.convert(jpaBorrowingRecord.getPatron()));
            borrowingRecord.setBorrowing_date(jpaBorrowingRecord.getBorrowing_date());
            borrowingRecord.setReturn_date(jpaBorrowingRecord.getReturn_date());
            borrowingRecord.setId(jpaBorrowingRecord.getId());
            borrowingRecords.add(borrowingRecord);

        }
        return borrowingRecords;
    }

    public RetriveBorrowingRecord findById(long book_id,long patron_id) {
        Optional<JpaBook> book = bookRepository.findById(book_id);
        Optional<JpaPatron> patron = patronRepository.findById(patron_id);
        Optional<JpaBorrowingRecord> jpaBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(book_id, patron_id);
        if (book.isPresent() && patron.isPresent() && jpaBorrowingRecord.isPresent()) {
            {
                RetriveBorrowingRecord borrowingRecord = new RetriveBorrowingRecord();
                borrowingRecord.setBook(bookMapper.convert(jpaBorrowingRecord.get().getBook()));
                borrowingRecord.setPatron(patronMapper.convert(jpaBorrowingRecord.get().getPatron()));
                borrowingRecord.setBorrowing_date(jpaBorrowingRecord.get().getBorrowing_date());
                borrowingRecord.setReturn_date(jpaBorrowingRecord.get().getReturn_date());
                borrowingRecord.setId(jpaBorrowingRecord.get().getId());
                return borrowingRecord;
            }
        }
        throw new RecordNotFoundException("The Record Of Book Or Patron Not Found");
    }

    @Transactional
    public BorrowingRecord insert(BorrowingRecord borrowingRecord,long bookId,long patronId) {

        Optional<JpaBook> book = bookRepository.findById(bookId);
//        Optional<JpaBook> book = bookRepository.findById(borrowingRecord.getBook_id());
        Optional<JpaPatron> patron = patronRepository.findById(patronId);
//        Optional<JpaPatron> patron = patronRepository.findById(borrowingRecord.getPatron_id());
        if(book.isPresent() && patron.isPresent()) {
            JpaBorrowingRecord jpaBorrowingRecord = new JpaBorrowingRecord();
            jpaBorrowingRecord.setPatron(patron.get());
            jpaBorrowingRecord.setBook(book.get());
            jpaBorrowingRecord.setBorrowing_date(new Date());
            jpaBorrowingRecord.setReturn_date(borrowingRecord.getReturn_date());
            borrowingRecordRepository.save(jpaBorrowingRecord);
            return borrowingRecord;
        }
        throw new RecordNotFoundException("The Record Of Book Or Patron Not Found");
    }

}
