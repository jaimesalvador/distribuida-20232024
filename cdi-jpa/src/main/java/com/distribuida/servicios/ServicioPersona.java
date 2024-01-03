package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {
    public void insert(Persona p);
    List<Persona> findAll();
}
