package com.distribuida.authors.repo;

import com.distribuida.authors.db.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAll();
    Author findById(Integer id);
    void persist(Author obj);
    void deleteById(Integer id);
}
