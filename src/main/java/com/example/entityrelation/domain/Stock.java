package com.example.entityrelation.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "col1", "col2", "col3", "col4", "col5", "col6", "col7", "col8"})
public class Stock {

    @Id
    @GeneratedValue
    @Column(name = "stock_id")
    private Long id;

    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String col8;
    private String col9;
    private String col10;
    private String col11;
    private String col12;
    private String col13;
    private String col14;
    private String col15;


    public Stock(Long id, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String col9, String col10, String col11, String col12, String col13, String col14, String col15) {
        this.id = id;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.col5 = col5;
        this.col6 = col6;
        this.col7 = col7;
        this.col8 = col8;
        this.col9 = col9;
        this.col10 = col10;
        this.col11 = col11;
        this.col12 = col12;
        this.col13 = col13;
        this.col14 = col14;
        this.col15 = col15;
    }
}
