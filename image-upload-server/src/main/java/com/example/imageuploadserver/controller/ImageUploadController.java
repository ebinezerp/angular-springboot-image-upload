package com.example.imageuploadserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/server")
public class ImageUploadController {

    @PostMapping("/file-upload")
    public ResponseEntity<Boolean> fileUploadToServer(@RequestParam("filename")String filename, @RequestPart("image")MultipartFile image){
       try {
           InputStream inputStream = image.getInputStream();
           // saves the files in same class path
           File imageDirectory = new File("images");
           if(!imageDirectory.exists()){
               imageDirectory.mkdirs();
           }
           FileOutputStream fileOutputStream = new FileOutputStream(imageDirectory.getAbsolutePath()+"/"+filename+".jpg");
           byte[] array = new byte[inputStream.available()];
           inputStream.read(array);
           fileOutputStream.write(array);
           fileOutputStream.flush();
           fileOutputStream.close();
           return new ResponseEntity<>(true,HttpStatus.OK);
       }catch (IOException e){
           throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Image is not received")  ;
       }
    }
}
