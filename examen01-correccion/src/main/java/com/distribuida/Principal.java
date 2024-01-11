package com.distribuida;

import com.distribuida.rest.BookRest;
import com.distribuida.servicios.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import static spark.Spark.*;

public class Principal {
    static SeContainer container;

    public static void main(String[] args) {

        container = SeContainerInitializer
                .newInstance()
                .initialize();

        port(8080);

        BookService servicio = container.select(BookService.class)
                .get();

        BookRest bookRest = new BookRest(servicio);

        ObjectMapper mapper = new ObjectMapper();

        get("/books", bookRest::findAll, mapper::writeValueAsString);
        get("/books/:id", bookRest::findById, mapper::writeValueAsString);
        post("/books", bookRest::insert, mapper::writeValueAsString);
        put("/books/:id", bookRest::update, mapper::writeValueAsString);
        delete("/boloks/:id", bookRest::remove, mapper::writeValueAsString);
    }
}
