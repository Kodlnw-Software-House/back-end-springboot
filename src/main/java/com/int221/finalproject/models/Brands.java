package com.int221.finalproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Brands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandid")
    @Getter@Setter
    private String brandId;

    @Getter@Setter
    @Column(name = "brandname")
    private String brandName;

//    @OneToMany(mappedBy="brands")
//    @Getter@Setter
//    private List<Products> products;
}
