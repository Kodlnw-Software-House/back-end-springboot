package com.int221.finalproject.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="brands")
public class Brands {

    @Id
    @Column(name = "brandid")
    @Getter
    private String brandId;

    @Getter
    @Column(name = "brandname")
    private String brandName;

}
