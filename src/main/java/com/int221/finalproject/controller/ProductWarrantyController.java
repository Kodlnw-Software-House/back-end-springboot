package com.int221.finalproject.controller;

import com.int221.finalproject.models.ProductWarranty;
import com.int221.finalproject.repository.ProductWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warranty")
public class ProductWarrantyController {
    @Autowired
    private ProductWarrantyRepository productWarrantyRepository;

    @GetMapping("/getall")
    public List<ProductWarranty> getAllWarranty(){
        return productWarrantyRepository.findAll();
    }
}
