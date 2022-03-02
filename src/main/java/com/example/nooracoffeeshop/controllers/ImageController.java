package com.example.nooracoffeeshop.controllers;

import java.io.IOException;
import java.util.Base64;

import com.example.nooracoffeeshop.Repositories.ImageRepository;
import com.example.nooracoffeeshop.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    
    @Autowired
    private ImageRepository imageRepository;
    //***Image addition ****

    @GetMapping("/admin/imageAddition")
    public String showImageAdditionForm(){
      return "image";
    }

    @PostMapping("/admin/images/save")
    public String saveImage(@RequestParam String name,@RequestParam String description,@RequestParam("image") MultipartFile image) throws IOException{
       Image image1 = new Image();
       image1.setName(name);
       image1.setDescription(description);
       image1.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
       imageRepository.save(image1);
       return "image";

    }
    
}
