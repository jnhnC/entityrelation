package com.example.entityrelation.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Book extends Item {

    private String author;
    private String isbn;

    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        this.name =  name;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
    }
}
