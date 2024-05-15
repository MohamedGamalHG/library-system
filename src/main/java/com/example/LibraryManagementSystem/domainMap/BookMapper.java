package com.example.LibraryManagementSystem.domainMap;

import com.example.LibraryManagementSystem.domains.dtos.Book;
import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
//        TypeMap<Category, CategoryJpa> type = this.modelMapper.createTypeMap(Category.class, CategoryJpa.class);
//        type.addMappings(mapper -> {
//            mapper.map(src -> src.getCategoryName(), CategoryJpa::setName);
//            //mapper.map(src -> src.getId(), CategoryJpa::setId);
//        });
    }
    public Book convert(JpaBook jpaBook)
    {
        return modelMapper.map(jpaBook, Book.class);
    }
    public JpaBook convert(Book book)
    {
        return modelMapper.map(book, JpaBook.class);
    }
}
