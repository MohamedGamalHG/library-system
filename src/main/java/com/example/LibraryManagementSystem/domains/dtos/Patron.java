package com.example.LibraryManagementSystem.domains.dtos;

import com.example.LibraryManagementSystem.domains.entities.JpaBook;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Patron {
    private Long id;
    @NotNull(message = "the name is required")
    private String name;
    @NotNull(message = "the address is required")
    private String address;
    @NotNull(message = "the phone is required")
    private String phone;
}
