package com.int221.finalproject.controller;

import com.int221.finalproject.exceptions.CustomException;
import com.int221.finalproject.exceptions.ExceptionResponse;
import com.int221.finalproject.models.Products;
import com.int221.finalproject.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (hasFoundId(id)) {
            return productsRepository.findById(id).get();
        }
        else throw new CustomException("Product Id :"+id+" does not exist !", ExceptionResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST);
    }

    @GetMapping("/getsearch")
    public List<Products> getProductBySearch(@RequestParam("search")String result){
        List<Products> products = productsRepository.findAll();
        products.removeIf(product ->  !product.getProductName().toLowerCase().contains(result.toLowerCase()));
        return products;
    }

    @GetMapping("/productWithPage")
    public List<Products> productWithPage(@RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "2") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Products> pageResult = productsRepository.findAll(pageable);
        return pageResult.getContent();
    }

    @PostMapping("/create")
    public Products createNewProduct(@RequestBody Products product){
        if (!hasFoundId(product.getProductCode())) {
            productsRepository.save(product);
            return product;
        }
        else throw new CustomException("Product Id :"+product.getProductCode()+" already exist !", ExceptionResponse.ERROR_CODE.PRODUCT_ALREADY_EXIST);
    }

    @PutMapping("/edit/{id}")
    public Products editProduct(@RequestBody Products product,@PathVariable int id){
        if (hasFoundId(id)) {
            productsRepository.save(product);
            return product;
        }
        else throw new CustomException("Product Id :"+id+" does not exist !", ExceptionResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id){
        if (hasFoundId(id)) {
            productsRepository.deleteById(id);
        }
        else throw new CustomException("Product Id :"+id+" does not exist !", ExceptionResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST);
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
