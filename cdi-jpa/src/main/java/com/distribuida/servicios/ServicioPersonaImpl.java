package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {
    @Inject
    EntityManager em;

    @Override
    public List<Persona> findAll() {
        return em.createQuery("select o from Persona o")
                .getResultList();
    }

    public void insert(Persona p) {
        var tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();
        }
    }
}
