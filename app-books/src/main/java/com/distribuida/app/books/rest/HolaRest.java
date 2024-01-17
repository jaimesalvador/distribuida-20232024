package com.distribuida.app.books.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;

@Path("/hola")
public class HolaRest {
    @Inject
    @ConfigProperty(name = "app.books.msg")
    private String message;

    @GET
    public String hola() {

        Config cfg = ConfigProvider.getConfig();
        //var newMsg = cfg.getValue("app.books.msg", String.class);

        cfg.getConfigSources()
                .forEach(s->{
                    System.out.printf("%d: %s\n", s.getOrdinal(), s.getName());
                });

        return String.format("%s %s", message, LocalDateTime.now());
    }
}
