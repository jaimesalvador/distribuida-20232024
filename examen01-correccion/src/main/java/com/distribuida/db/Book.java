package com.distribuida.db;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String isbn;
    private String title;
    private String author;
    private BigDecimal price;
}
