package com.distribuida.app.books.clients;

import com.distribuida.app.books.dtos.AuthorDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "http://localhost:9090")
//@RegisterRestClient
@RegisterRestClient(configKey = "AuthorRestClient")
public interface AuthorRestClient {
    @GET
    public List<AuthorDto> findAll();

    @GET
    @Path("/{id}")
    public AuthorDto findById(@PathParam("id") Integer id);
}
