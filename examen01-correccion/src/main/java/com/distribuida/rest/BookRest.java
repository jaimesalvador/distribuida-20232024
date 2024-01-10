package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.halt;

public class BookRest {

    BookService servicio;

    public BookRest(BookService servicio) {
        this.servicio = servicio;
    }

    public List<Book> findAll(Request req, Response res) {
        res.type("application/json");
        return servicio.findAll();
    }

    public Book findById(Request req, Response res) {
        res.type("application/json");

        var _id = req.params(":id");

        var obj = servicio.findById(Integer.valueOf(_id));
        if(obj==null) {
            halt(404, "Libro no encontrado");
        }
        return obj;
    }

    public Object remove(Request req, Response res) {
        res.type("application/json");

        var _id = req.params(":id");

        var obj = servicio.findById(Integer.valueOf(_id));
        if(obj==null) {
            halt(404, "Libro no encontrado");
        }

        servicio.remove( Integer.valueOf(_id) );

        return "Objeto eliminado";
    }

    public Object insert(Request req, Response res) {
        res.type("application/json");

        String body = req.body();

        ObjectMapper mapper = new ObjectMapper();

        try {
            var book = mapper.readValue(body, Book.class);
            servicio.insert(book);
        } catch (Exception e) {
            halt(404, "No es posible crear el libro");
            throw new RuntimeException(e);
        }

        return "Objeto creado";
    }

    public Object update(Request req, Response res) {
        res.type("application/json");

        var _id = req.params(":id");

        //var obj = servicio.findById(Integer.valueOf(_id));

        String body = req.body();

        ObjectMapper mapper = new ObjectMapper();

        try {

            var book = mapper.readValue(body, Book.class);
            book.setId(Integer.valueOf(_id));

            servicio.update(book);
        } catch (Exception e) {
            halt(404, "No es posible actualizar el libro");
            throw new RuntimeException(e);
        }

        return "Objeto actualizo";
    }
}
