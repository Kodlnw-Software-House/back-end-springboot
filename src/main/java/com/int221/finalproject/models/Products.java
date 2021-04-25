package com.int221.finalproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Products {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="productcode")
    @Getter
    @Setter
    private int productCode;

    @Getter
    @Setter
    @Column(name ="productname")
    private String productName;

    @Getter
    @Setter
    @Column(name ="productdescription")
    private String productDescription;

    @Getter
    @Setter
    @Column(name ="productprice")
    private double productPrice;

    @Setter
    @Getter
    @Column(name ="productmanufactureddate")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="brandid", nullable=false)
    @Getter
    @Setter
    private Brands brands;

    @OneToOne
    @JoinColumn(name="warrantyid")
    @Getter
    @Setter
    private ProductWarranty productWarranty;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "productcolor",
            joinColumns = @JoinColumn(name = "productcode"),
            inverseJoinColumns = @JoinColumn(name = "colorid")
    )
    @Getter
    @Setter
    private List<Colors> colors = new ArrayList<>();
}
