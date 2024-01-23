package com.distribuida.app.books.dtos;

import com.distribuida.app.books.db.Book;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BookDto {
    private Integer id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private String authorName;

    public static BookDto from(Book obj) {
        BookDto ret = new BookDto();

        ret.setId(obj.getId());
        ret.setIsbn(obj.getIsbn());
        ret.setPrice(obj.getPrice());
        ret.setTitle(obj.getTitle());

        return ret;
    }
}
