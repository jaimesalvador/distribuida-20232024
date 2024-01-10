package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject EntityManager em;

    @Override
    public List<Book> findAll() {
        return em.createQuery("select o from Book o order by o.title")
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
            tx.rollback();;
        }
    }

    @Override
    public void update(Book obj) {
        var tx = em.getTransaction();
        try {
            tx.begin();

            em.merge(obj);

//            var o2 = em.find(Book.class, obj.getId());
//            o2.setIsbn( );
//            o2.setAuthor();
//            o2.setTitle();
//            o2.setPrice();

            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();;
        }
    }

    @Override
    public void remove(Integer id) {
        var tx = em.getTransaction();
        try {
            tx.begin();

            //Book obj = new Book();
            //obj.setId(id);

            var obj = em.getReference(Book.class, id);

            em.remove(obj);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();;
        }
    }
}
