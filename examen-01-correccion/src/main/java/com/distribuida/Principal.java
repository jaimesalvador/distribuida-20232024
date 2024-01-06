package com.distribuida;

import com.distribuida.rest.BookRest;
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

        BookRest bookRest = new BookRest(container);

        ObjectMapper mapper = new ObjectMapper();

        port(8080);

        get("/books", bookRest::findAll, mapper::writeValueAsString);
        get("/books/:id", bookRest::findById, mapper::writeValueAsString);
        post("/books", bookRest::create, mapper::writeValueAsString);
        put("/books/:id", bookRest::update, mapper::writeValueAsString);
        delete("/books/:id", bookRest::remove, mapper::writeValueAsString);
    }
}
