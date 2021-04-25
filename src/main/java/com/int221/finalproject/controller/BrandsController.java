package com.int221.finalproject.controller;

import com.int221.finalproject.models.Brands;
import com.int221.finalproject.repository.BrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandsController {

    @Autowired
    private BrandsRepository brandsRepository;

    @GetMapping("/getall")
    public List<Brands> getAllBrand(){
        return brandsRepository.findAll();
    }

}
