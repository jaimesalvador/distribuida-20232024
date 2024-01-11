package com.distribuida.app.books.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;

@Path("/hola")
public class HolaRest {

    @GET
    public String hola() {
        return String.format("Hola %s", LocalDateTime.now());
    }
}
