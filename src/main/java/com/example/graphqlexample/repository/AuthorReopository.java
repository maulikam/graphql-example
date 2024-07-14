package com.example.graphqlexample.repository;

import com.example.graphqlexample.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorReopository extends JpaRepository <Author, Long> {

}
