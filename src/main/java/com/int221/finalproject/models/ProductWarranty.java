package com.int221.finalproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productwarranty")
public class ProductWarranty {
    @Id
    @Column(name ="warrantyid")
    @Getter@Setter
    private int warrantyId;

    @Column(name="warrantydescription")
    @Getter@Setter
    private String warrantyDescription;

}
