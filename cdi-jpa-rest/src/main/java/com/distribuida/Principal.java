package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class Principal {
    static SeContainer container;

    static List<Persona> listarPersonas(Request req, Response res) {
        var servicio = container.select(ServicioPersona.class)
                .get();
        res.type("application/json");

        return servicio.findAll();
    }

    static Persona buscarPersona(Request req, Response res) {
        var servicio = container.select(ServicioPersona.class)
                .get();
        res.type("application/json");

        String _id = req.params(":id");

        var persona =  servicio.findById(Integer.valueOf(_id));

        if(persona==null) {
            // 404
            halt(404, "Persona no encontrada");
        }

        return persona;
    }

    public static void main(String[] args) {

        container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class)
                .get();

        port(8080);

        //get("/hello", (req, res) -> "Hello World");

        Gson gson = new Gson();
        get("/personas", Principal::listarPersonas, gson::toJson);
        get("/personas/:id", Principal::buscarPersona, gson::toJson);
    }
}
