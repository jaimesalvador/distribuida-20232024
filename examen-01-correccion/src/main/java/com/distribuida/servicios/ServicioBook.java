package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface ServicioBook {
    List<Book> findAll();
    Book findById(Integer id);

    void insert(Book obj);
    void update(Book obj);
    void remove(Integer id);
}
