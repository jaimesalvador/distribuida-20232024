package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Principal {
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class)
                .get();

        Persona p = new Persona();
        p.setId(1);
        p.setNombre("nombre1");
        p.setDireccion("direccion1");
        p.setEdad(1);

        servicio.insert( p );

        servicio.findAll()
                .stream()
                .map(Persona::getNombre)
                .forEach(System.out::println);
    }
}
