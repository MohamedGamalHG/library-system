package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.domainMap.BookMapper;
import com.example.LibraryManagementSystem.domains.dtos.Book;
import com.example.LibraryManagementSystem.domains.dtos.Patron;
import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import com.example.LibraryManagementSystem.exceptionHandling.DuplicateException;
import com.example.LibraryManagementSystem.exceptionHandling.GeneralException;
import com.example.LibraryManagementSystem.exceptionHandling.RecordNotFoundException;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public BookService(BookRepository repository, BookMapper bookMapper)
    {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    public List<Book> findAll()
    {
        // without use stream
        /*
            List<Book> books = new ArrayList<>();
            for (JpaBook book:repository.findAll()) {
                books.add(bookMapper.convert(book));
            }
        */

        List<Book> books = repository.findAll().stream().map(e -> bookMapper.convert(e)).toList();
        return books;
    }
    public Book findById(long id)
    {
        Optional<JpaBook> jpaBook = repository.findById(id);
        if (jpaBook.isPresent())
        {
            return bookMapper.convert(jpaBook.get());
        }
        throw new RecordNotFoundException("This Record Is Not Found Of Id = "+ id);
    }

    public Book insert(Book book)
    {
       if(repository.countByIsbn(book.getIsbn()) > 0)
           throw new DuplicateException("This Isbn is Duplicated");
       JpaBook book1 = bookMapper.convert(book);
       repository.save(book1);
       return book;
    }

    public Book update(Book book)
    {
        try {
            Optional<JpaBook> jpaBook = repository.findById(book.getId());

            if (jpaBook.isPresent()) {
                JpaBook jpaBook1 = bookMapper.convert(book);
                repository.save(jpaBook1);
                return book;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+book.getId());
        }catch (Exception ex)
        {
            //logger.error(ex.getMessage());
           throw new GeneralException(ex.getMessage());
        }
    }
    public void delete(Long id)
    {
        Optional<JpaBook> jpaBook = repository.findById(id);
        if(jpaBook.isPresent())
            repository.deleteById(id);
        else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);

    }


}
