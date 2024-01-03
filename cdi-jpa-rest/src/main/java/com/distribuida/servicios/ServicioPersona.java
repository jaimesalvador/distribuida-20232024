package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {
    void insert(Persona p);
    List<Persona> findAll();
    Persona findById(Integer id);
}
