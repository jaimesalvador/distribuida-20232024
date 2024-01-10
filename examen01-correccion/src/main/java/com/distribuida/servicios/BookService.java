package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {
    //--R
    List<Book> findAll();
    Book findById(Integer id);

    //--C
    void insert(Book obj);

    //--U
    void update(Book obj);

    //--D
    void remove(Integer id);
}
