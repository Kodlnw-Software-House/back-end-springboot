package com.int221.finalproject.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="colors")
public class Colors {
    @Id
    @Column(name ="colorid")
    @Getter
    private int colorId;

    @Getter
    @Column(name ="colorname")
    private String colorName;

    @Getter
    @Column(name ="colorhex")
    private String colorHex;

}
