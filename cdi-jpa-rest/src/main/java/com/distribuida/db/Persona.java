package com.distribuida.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="persona")
@Data
public class Persona {
    @Id
    private Integer id;

    private String nombre;
    private String direccion;
    private Integer edad;
}
