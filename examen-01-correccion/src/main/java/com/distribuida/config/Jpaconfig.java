package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class Jpaconfig {
    private EntityManagerFactory  emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("pu-distribuida");
    }

    @Produces
    public EntityManager em() {
        return emf.createEntityManager();
    }
}
