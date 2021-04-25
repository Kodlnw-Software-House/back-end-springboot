package com.int221.finalproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="colorid")
    @Getter
    @Setter
    private int colorId;

    @Getter
    @Setter
    @Column(name ="colorname")
    private String colorName;

    @Getter
    @Setter
    @Column(name ="colorhex")
    private String colorHex;

}
