package com.int221.finalproject.controller;

import com.int221.finalproject.models.Colors;
import com.int221.finalproject.repository.ColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorsController {
    @Autowired
    private ColorsRepository colorsRepository;

    @GetMapping("/getall")
    public List<Colors> getAllProduct(){
        return colorsRepository.findAll();
    }
}
