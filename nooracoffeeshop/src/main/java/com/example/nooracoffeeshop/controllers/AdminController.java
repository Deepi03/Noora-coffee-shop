package com.example.nooracoffeeshop.controllers;

import com.example.nooracoffeeshop.model.Supplier;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

import com.example.nooracoffeeshop.model.Department;
import com.example.nooracoffeeshop.model.DepartmentRepository;
import com.example.nooracoffeeshop.model.Image;
import com.example.nooracoffeeshop.model.ImageRepository;
import com.example.nooracoffeeshop.model.Manufacturer;
import com.example.nooracoffeeshop.model.ManufacturerRepository;
import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.model.Supplier;
import com.example.nooracoffeeshop.model.ProductRepository;
import com.example.nooracoffeeshop.model.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import  java.util.List;





@Controller
public class AdminController {


    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    ManufacturerRepository manufacturerRepository; 

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ImageRepository imageRepository;



    // **** Admin home *****


    @GetMapping("/admin")
    public String showAdminPage(){

      return "admin_login";
     
    }

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


    // ***** product management ******

// add product
    @GetMapping("/admin/productManagement")
    public String showProductAdditionForm(Model model){
          
       
          model.addAttribute("manufacturers",manufacturerRepository.findAll());
          model.addAttribute("departments", departmentRepository.findAll());
          model.addAttribute("suppliers", supplierRepository.findAll());
          model.addAttribute("products", productRepository.findAll());
          
          // also displaying product table
        return "productManagement";
        
    }

    @PostMapping("/admin/product")
    public String addProduct(@RequestParam Long manufacturerID, @RequestParam String productName , @RequestParam String productDescription , @RequestParam Long departmentID ,@RequestParam Long supplierID , @RequestParam  Double productPrice,@RequestParam String name,@RequestParam String description,@RequestParam("image") MultipartFile image ) throws IOException
    {

    
           Product product = new Product();
           Image image1 = new Image();
           image1.setName(name);
           image1.setDescription(description);
           image1.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
           product.setName(productName);
           product.setPrice(productPrice);
           product.setDescription(productDescription);
           product.setDepartment(departmentRepository.getById(departmentID));
           product.setManufacturer(manufacturerRepository.getById(manufacturerID));
           product.setSupplier(supplierRepository.getById(supplierID));
           product.setImages(Arrays.asList(image1));
           imageRepository.save(image1);
          productRepository.save(product);
       return "productManagement";
    }



   
//update_product

@GetMapping("/updateproduct/{id}")
public String showFormForUpdateProduct(@PathVariable Long id , Model model){
    Product product = productRepository.getById(id);
    model.addAttribute("manufacturers",manufacturerRepository.findAll());
    model.addAttribute("departments", departmentRepository.findAll());
    model.addAttribute("suppliers", supplierRepository.findAll());
   
    model.addAttribute("product", product);
    return "updateproduct";

}


@PostMapping("/updateproduct/{id}") 
public String                     update(@RequestParam Long manufacturerID, @RequestParam String productName , @RequestParam String productDescription , @RequestParam Long departmentID ,@RequestParam Long supplierID , @RequestParam  Double productPrice ,@PathVariable Long id){
 

       Product existingProduct = productRepository.getById(id);
 
       existingProduct.setDepartment(departmentRepository.getById(departmentID));
       existingProduct.setManufacturer(manufacturerRepository.getById(manufacturerID));
       existingProduct.setSupplier(supplierRepository.getById(supplierID));
       existingProduct.setName(productName);
       existingProduct.setPrice(productPrice);
       existingProduct.setDescription(productDescription);


      productRepository.save(existingProduct); 

        return "redirect:/productManagement";
     
}

// Delete_product


@GetMapping("/deleteproduct/{id}")
public String deleteProduct(@PathVariable Long id){
  productRepository.deleteById(id);
  return "productManagement";
}


    // ***** supplier management ******


    //add supplier & display supplier table
    @GetMapping("/admin/supplierManagement")
    public String displaySupplier(Model model){
      model.addAttribute("suppliers", supplierRepository.findAll());
      return "supplierManagement";
    }

    @PostMapping("/admin/supplier")
    public String addSupplier(@RequestParam String supplierName,@RequestParam String contactMail ,@RequestParam String contactName){
      Supplier supplier = new Supplier();
      supplier.setName(supplierName);
      supplier.setContactPersonEmail(contactMail);
      supplier.setContactPersonName(contactName);
      supplierRepository.save(supplier);
      return "supplierManagement";
    }


    // update supplier

    @GetMapping("/updatesupplier/{id}")
    public String showFormForUpdateSupplier(@PathVariable Long id , Model model){
  
      Supplier supplier = supplierRepository.getById(id);
      model.addAttribute("supplier", supplier);

      return "updateSupplier";

    }

    @PostMapping("/updatesupplier/{id}")
    public String updateSupplier(@RequestParam String supplierName,@RequestParam String contactMail ,@RequestParam String contactName,@PathVariable Long id){

      Supplier existingSupplier = supplierRepository.getById(id);
      existingSupplier.setContactPersonEmail(contactMail);
      existingSupplier.setContactPersonName(contactName);
      existingSupplier.setName(supplierName);

      supplierRepository.save(existingSupplier);

      return "redirect:/supplierManagement";

    }

    // Delete_supplier


@GetMapping("/deletesupplier/{id}")
public String deleteSupplier(@PathVariable Long id){
  supplierRepository.deleteById(id);
  return "redirect:/supplierManagement";
}



    // ***** Manufacturer management ******

    @GetMapping("/admin/manufacturerManagement")
    public String displayManufacturer(Model model){

      model.addAttribute("manufacturers", manufacturerRepository.findAll());


      return "manufacturerManagement";
    }

    @PostMapping("/admin/manufacturer")
    public String addManufacturer(@RequestParam String manufacturerName,@RequestParam String manufacturerUrl){

    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setName(manufacturerName);
    manufacturer.setUrl(manufacturerUrl);
    manufacturerRepository.save(manufacturer);
    return "redirect:/anufacturerManagement";
    }


    // update supplier

    @GetMapping("/updatemanufacturer/{id}")
    public String showFormForUpdateManufacturer(@PathVariable Long id , Model model){
  
      Manufacturer manufacturer = manufacturerRepository.getById(id);
      model.addAttribute("manufacturer", manufacturer);

      return "updateManufacturer";

    }

    @PostMapping("/updateManufacturer/{id}")
    public String updateManufacturer(@RequestParam String manufacturerName,@RequestParam String manufacturerUrl ,@PathVariable Long id){

      Manufacturer existingManufacturer = manufacturerRepository.getById(id);
      existingManufacturer.setName(manufacturerName);
      existingManufacturer.setUrl(manufacturerUrl);
 

      manufacturerRepository.save(existingManufacturer);

      return "redirect:/manufacturerManagement";

    }
     
  
    // Delete_supplier


@GetMapping("/deletemanufacturer/{id}")
public String deleteManufacturer(@PathVariable Long id){
  manufacturerRepository.deleteById(id);
  return "redirect:/manufacturerManagement";
}
  
    
}
