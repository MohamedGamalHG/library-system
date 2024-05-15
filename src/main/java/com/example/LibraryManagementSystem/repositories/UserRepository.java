package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.domains.entities.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<JpaUser,Long> {
    JpaUser findByUsername(String name);

}
