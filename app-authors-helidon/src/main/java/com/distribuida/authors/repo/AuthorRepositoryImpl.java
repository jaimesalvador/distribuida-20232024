package com.distribuida.authors.repo;

import com.distribuida.authors.db.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Author> findAll() {
        return em.createQuery("select o from Author o", Author.class)
                .getResultList();
    }

    @Override
    public Author findById(Integer id) {
        return em.find(Author.class, id);
    }

    @Override
    public void persist(Author obj) {
        em.persist(obj);
    }

    @Override
    public void deleteById(Integer id) {
        var obj = em.getReference(Author.class, id);
        em.remove(obj);
    }
}
