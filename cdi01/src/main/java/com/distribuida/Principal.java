package com.distribuida;

import com.distribuida.servicios.Hola;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Principal {
    public static void main(String[] args) {

        SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize();

        Instance<Hola>  obj = container.select(Hola.class);
        Hola servicio = obj.get();
        System.out.println(servicio);

        String str = servicio.test("mundo");
        System.out.println(str);
    }
}
