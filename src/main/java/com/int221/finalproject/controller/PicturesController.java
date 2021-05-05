package com.int221.finalproject.controller;

import com.int221.finalproject.exceptions.CustomException;
import com.int221.finalproject.exceptions.ExceptionResponse;
import com.int221.finalproject.models.Products;
import com.int221.finalproject.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/picture")
public class PicturesController {

    @Autowired
    private ProductsRepository productsRepository;

    private static final String PICTURE_PATH = "./public/pictures/";

    private static final String FORM_1 = " does not exist !";
    private static final String FORM_2 = "Product Id :";

    @GetMapping("/get/{id:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id")String id){
        try {
            FileInputStream fi = new FileInputStream(PICTURE_PATH+id);
            byte[] image = fi.readAllBytes();
            fi.close();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        }
        catch (FileNotFoundException e){
            throw new CustomException(id+FORM_1,ExceptionResponse.ERROR_CODE.IMAGE_DOES_NOT_EXIST);
        }
        catch (IOException e){
            throw new CustomException(e.toString(),ExceptionResponse.ERROR_CODE.IO_ERROR);
        }
    }

    @PostMapping ("/add/{id}")
    public ResponseEntity<Object> fileUpload(@RequestParam("File")MultipartFile file,@PathVariable("id")int id){
        try {
            if (hasFoundId(id)) {
                Path path = Paths.get(PICTURE_PATH);
                File dir = new File(PICTURE_PATH);
                if (!dir.exists()) Files.createDirectories(path);
                String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                File myFile = new File(PICTURE_PATH + id + fileType);
                if(myFile.createNewFile()) {
                    FileOutputStream fos = new FileOutputStream(myFile);
                    fos.write(file.getBytes());
                    fos.close();
                    return new ResponseEntity<>("The File Uploaded Successfully.", HttpStatus.OK);
                }else
                    throw new CustomException(FORM_2+id+" Image Already Exist.",ExceptionResponse.ERROR_CODE.IMAGE_ALREADY_EXIST);
            }
            throw new CustomException(FORM_2+id+FORM_1,ExceptionResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST);
        }catch (IOException e){
            throw new CustomException(e.toString(),ExceptionResponse.ERROR_CODE.IO_ERROR);
        }
    }

    @PutMapping("/edit/{id:.+}")
    public void changeImage(@RequestParam("File")MultipartFile file,@PathVariable("id")String id)throws IOException {
        try {
            String[] fileName = id.split("\\.(?=[^\\.]+$)");
            int hasId = parseInt(fileName[0]);
            if(hasFoundId(hasId)) {
                File foundFile = new File(PICTURE_PATH + id);
                if(foundFile.exists() && !foundFile.isDirectory()){
                    this.deleteImage(id);
                }
                this.fileUpload(file,hasId);
                return;
            }
            throw new CustomException(id+FORM_1,ExceptionResponse.ERROR_CODE.PRODUCT_DOES_NOT_EXIST);
        }
        catch (NumberFormatException e){
            throw new CustomException(e.toString(),ExceptionResponse.ERROR_CODE.IMAGE_DOES_NOT_EXIST);
        }
    }

    @DeleteMapping("/delete/{id:.+}")
    public void deleteImage(@PathVariable("id")String id)throws NoSuchFileException, DirectoryNotEmptyException {
        try {
            String[] idString = id.split("\\.(?=[^\\.]+$)");
            int hasId = parseInt(idString[0]);
            if (hasFoundId(hasId)){
                File myFile = new File(PICTURE_PATH + id);
                if (myFile.delete()){
                    return;
                }
                else {
                    throw new CustomException("File Delete Failed.",ExceptionResponse.ERROR_CODE.IO_ERROR);
                }
            }
            throw new CustomException(id+FORM_1,ExceptionResponse.ERROR_CODE.IMAGE_DOES_NOT_EXIST);
        }
        catch (NumberFormatException e){
            throw new CustomException(e.toString(),ExceptionResponse.ERROR_CODE.IMAGE_DOES_NOT_EXIST);
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
