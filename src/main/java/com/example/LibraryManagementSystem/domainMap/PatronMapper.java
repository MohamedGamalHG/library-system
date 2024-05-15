package com.example.LibraryManagementSystem.domainMap;

import com.example.LibraryManagementSystem.domains.dtos.Book;
import com.example.LibraryManagementSystem.domains.dtos.Patron;
import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper {
    private final ModelMapper modelMapper;

    public PatronMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
//        TypeMap<Category, CategoryJpa> type = this.modelMapper.createTypeMap(Category.class, CategoryJpa.class);
//        type.addMappings(mapper -> {
//            mapper.map(src -> src.getCategoryName(), CategoryJpa::setName);
//            //mapper.map(src -> src.getId(), CategoryJpa::setId);
//        });
    }
    public Patron convert(JpaPatron jpaPatron)
    {
        return modelMapper.map(jpaPatron, Patron.class);
    }
    public JpaPatron convert(Patron patron)
    {
        return modelMapper.map(patron, JpaPatron.class);
    }
}
