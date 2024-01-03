package com.distribuida.servicios;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HolaImpl implements Hola {
    @Override
    public String test(String msg) {
        return "Hola " + msg;
    }
}
