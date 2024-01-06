package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioBookImpl implements ServicioBook {
    @Inject
    EntityManager em;

    @Override
    public List<Book> findAll() {
        return em.createQuery("select o from Book o")
                .getResultList();
    }

    @Override
    public Book findById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public void insert(Book obj) {
        var tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(obj);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();
        }
    }

    @Override
    public void update(Book obj) {

    }

    @Override
    public void remove(Integer id) {
        var tx = em.getTransaction();

        try {
            tx.begin();
            var obj = em.getReference(Book.class, id);
            em.remove(obj);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();
        }
    }
}
