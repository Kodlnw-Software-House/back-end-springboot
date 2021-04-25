package com.int221.finalproject.controller;

import com.int221.finalproject.models.Products;
import com.int221.finalproject.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/getall")
    public List<Products> getAllProduct() {
        return productsRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Products getProductById(@PathVariable int id) {
        return productsRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public Products createNewProduct(@RequestBody Products product){
        productsRepository.save(product);
        return product;
    }

    @PutMapping("/edit/{id}")
    public Products editProduct(@RequestBody Products product,@PathVariable int id){
        if (hasFoundId(id)) {
            productsRepository.save(product);
            return product;
        }
        else
            return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        if (hasFoundId(id)) {
            productsRepository.deleteById(id);
        }
    }


    public boolean hasFoundId(int id){
        List<Products> products = productsRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductCode() == id){
                return true;
            }
        }
        return false;
    }
}
