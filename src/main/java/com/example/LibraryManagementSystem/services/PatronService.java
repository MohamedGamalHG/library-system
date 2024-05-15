package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.domainMap.PatronMapper;
import com.example.LibraryManagementSystem.domains.dtos.Patron;

import com.example.LibraryManagementSystem.domains.entities.JpaPatron;

import com.example.LibraryManagementSystem.exceptionHandling.GeneralException;
import com.example.LibraryManagementSystem.exceptionHandling.RecordNotFoundException;

import com.example.LibraryManagementSystem.repositories.PatronRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRepository repository;
    private final PatronMapper patronMapper;

    public PatronService(PatronRepository repository, PatronMapper patronMapper)
    {
        this.repository = repository;
        this.patronMapper = patronMapper;
    }

    public List<Patron> findAll()
    {

        // without use stream
        /*
            List<Patron> Patrons = new ArrayList<>();
            for (JpaPatron Patron:repository.findAll()) {
                Patrons.add(patronMapper.convert(Patron));
            }
        */
        List<Patron> Patrons = repository.findAll().stream().map(e -> patronMapper.convert(e)).toList();
        return Patrons;
    }
    public Patron findById(long id)
    {
        Optional<JpaPatron> JpaPatron = repository.findById(id);
        if (JpaPatron.isPresent())
        {
            return patronMapper.convert(JpaPatron.get());
        }
        throw new RecordNotFoundException("This Record Is Not Found Of Id = "+ id);
    }

    public Patron insert(Patron Patron)
    {
        JpaPatron Patron1 = patronMapper.convert(Patron);
            repository.save(Patron1);
            return Patron;
    }

    public Patron update(Patron Patron)
    {
        try {
            Optional<JpaPatron> JpaPatron = repository.findById(Patron.getId());

            if (JpaPatron.isPresent()) {
                JpaPatron JpaPatron1 = patronMapper.convert(Patron);
                repository.save(JpaPatron1);
                return Patron;
            }
            else
                throw new RecordNotFoundException("This Record Is Not Found Of Id = "+Patron.getId());
        }catch (Exception ex)
        {
            //logger.error(ex.getMessage());
            throw new GeneralException(ex.getMessage());
        }
    }
    public void delete(Long id)
    {
        Optional<JpaPatron> jpaPatron = repository.findById(id);
        if(jpaPatron.isPresent())
            repository.deleteById(id);
        else
            throw new RecordNotFoundException("This Record Is Not Found Of Id = "+id);
    }
}
