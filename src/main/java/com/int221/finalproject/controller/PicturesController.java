package com.int221.finalproject.controller;

import com.int221.finalproject.models.Products;
import com.int221.finalproject.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/picture")
public class PicturesController {

    @Autowired
    private ProductsRepository productsRepository;

    private final String PICTURE_PATH = "./pictures/";

    @GetMapping("/get/{id:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id) throws IOException {
        FileInputStream fi = new FileInputStream(PICTURE_PATH+id);
        byte[] image = fi.readAllBytes();
        fi.close();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @PostMapping ("/add/{id}")
    public ResponseEntity<Object> fileUpload(@RequestParam("File")MultipartFile file,@PathVariable("id")int id)throws IOException{
        if(hasFoundId(id)){
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File myFile = new File(PICTURE_PATH + id+fileType);
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
        return  new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/edit/{id:.+}")
    public void changeImage(@RequestParam("File")MultipartFile file,@PathVariable("id")String id)throws IOException {
        String FileName[] = id.split("\\.(?=[^\\.]+$)");
        int hasId = parseInt(FileName[0]);
        if(hasFoundId(hasId)) {
            this.deleteImage(id);
            this.fileUpload(file,hasId);
        }
    }

    @DeleteMapping("/delete/{id:.+}")
    public void deleteImage(@PathVariable("id")String id){
        String idString[] = id.split("\\.(?=[^\\.]+$)");
        int hasId = parseInt(idString[0]);
        if (hasFoundId(hasId)){
            File myFile = new File(PICTURE_PATH + id);
            myFile.delete();
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
