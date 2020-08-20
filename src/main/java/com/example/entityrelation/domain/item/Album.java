package com.example.entityrelation.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
public class Album extends Item {

    private String artist;
    private String etc;

    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        super();
        this.name =  name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;

    }
}
