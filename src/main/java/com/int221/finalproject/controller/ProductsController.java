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

import java.util.HashMap;
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

    @GetMapping("/searchProductWithPage")
    public List<Products> searchProductWithPage(@RequestParam(required = false,name = "title") String title,
                                          @RequestParam(defaultValue = "0") Integer pageNo,
                                          @RequestParam(defaultValue = "2") Integer pageSize){
        Page<Products> pageResult;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (title != null){
            pageResult = productsRepository.findByProductNameContains(title, pageable);
        }
        else{
            pageResult = productsRepository.findAll(pageable);
        }
        return pageResult.getContent();
    }

    @GetMapping("/pageSearchInfo")
    public HashMap<String, Integer> pageSearchInfo(@RequestParam(required = false,name = "title") String title,
                                   @RequestParam(defaultValue = "2") Integer pageSize){
        Page<Products> pageResult;
        Pageable pageable = PageRequest.of(0, pageSize);
        if (title != null){
            pageResult = productsRepository.findByProductNameContains(title, pageable);
        }
        else{
            pageResult = productsRepository.findAll(pageable);
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("totalPage",pageResult.getTotalPages());
        map.put("totalElements",(int) pageResult.getTotalElements());
        return map;
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
        if (hasFoundId(id) && (id == product.getProductCode())) {
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
