package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.ServicioBook;
import jakarta.enterprise.inject.se.SeContainer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.halt;

public class BookRest {

    SeContainer container;

    public BookRest(SeContainer container) {
        this.container = container;
    }

    public List<Book> findAll(Request req, Response res) {
        var servicio = container.select(ServicioBook.class)
                .get();
        res.type("application/json");

        return servicio.findAll();
    }

    public Book findById(Request req, Response res) {
        var servicio = container.select(ServicioBook.class)
                .get();
        res.type("application/json");

        String _id = req.params(":id");

        var obj =  servicio.findById(Integer.valueOf(_id));

        if(obj==null) {
            // 404
            halt(404, "Objeto no encontrada");
        }

        return obj;
    }

    public Object remove(Request req, Response res) {
        var servicio = container.select(ServicioBook.class)
                .get();
        res.type("application/json");

        String _id = req.params(":id");

        var obj =  servicio.findById(Integer.valueOf(_id));

        if(obj==null) {
            // 404
            halt(404, "Persona no encontrada");
        }

        servicio.remove(Integer.valueOf(_id));

        return "Objeto eliminado";
    }

    public Object create(Request req, Response res) {
        return "Objeto creado";
    }

    public Object update(Request req, Response res) {
        return "Objeto creado";
    }
}
