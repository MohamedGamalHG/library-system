package com.example.LibraryManagementSystem.domainMap;

import com.example.LibraryManagementSystem.domains.dtos.BorrowingRecord;
import com.example.LibraryManagementSystem.domains.dtos.Patron;
import com.example.LibraryManagementSystem.domains.entities.JpaBorrowingRecord;
import com.example.LibraryManagementSystem.domains.entities.JpaPatron;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowingRecordMapper {
    private final ModelMapper modelMapper;

    public BorrowingRecordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
//        TypeMap<Category, CategoryJpa> type = this.modelMapper.createTypeMap(Category.class, CategoryJpa.class);
//        type.addMappings(mapper -> {
//            mapper.map(src -> src.getCategoryName(), CategoryJpa::setName);
//            //mapper.map(src -> src.getId(), CategoryJpa::setId);
//        });
    }
    public BorrowingRecord convert(JpaBorrowingRecord borrowingRecord)
    {
        return modelMapper.map(borrowingRecord, BorrowingRecord.class);
    }
    public JpaBorrowingRecord convert(BorrowingRecord borrowingRecord)
    {
        return modelMapper.map(borrowingRecord, JpaBorrowingRecord.class);
    }
}
