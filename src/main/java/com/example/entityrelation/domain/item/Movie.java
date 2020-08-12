package com.example.entityrelation.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
