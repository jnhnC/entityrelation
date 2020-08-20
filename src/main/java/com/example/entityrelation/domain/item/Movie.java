package com.example.entityrelation.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
public class Movie extends Item {

    private String director;
    private String actor;

    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        super();
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.director = director;
        this.actor = actor;


    }
}
