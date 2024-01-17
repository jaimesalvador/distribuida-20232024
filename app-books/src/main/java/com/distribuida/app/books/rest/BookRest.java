package com.distribuida.app.books.rest;

import com.distribuida.app.books.db.Book;
import com.distribuida.app.books.repo.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class BookRest {
    @Inject
    BookRepository repo;

    @GET
    public List<Book> findAll() {
        return repo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        var book = repo.findByIdOptional(id);

        if(book.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book.get()).build();
    }

    @POST
    public Response create(Book obj) {
        obj.setId(null);

        repo.persist(obj);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Integer id, Book obj) {

        Book b = repo.findById(id);

        b.setIsbn(obj.getIsbn());
        b.setTitle(obj.getTitle());
        b.setPrice(obj.getPrice());
        b.setAuthor(obj.getAuthor());

        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        repo.deleteById(id);

        return Response.ok()
                .build();
    }
}
