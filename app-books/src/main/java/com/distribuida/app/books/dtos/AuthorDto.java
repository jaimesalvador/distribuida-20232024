package com.distribuida.app.books.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
