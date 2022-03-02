// package com.example.nooracoffeeshop.controllers;

// import com.example.nooracoffeeshop.model.Supplier;
// import com.example.nooracoffeeshop.services.DepartmentService;
// import com.example.nooracoffeeshop.services.ManufacturerService;
// import com.example.nooracoffeeshop.services.SupplierService;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.Base64;


// import com.example.nooracoffeeshop.Repositories.DepartmentRepository;
// import com.example.nooracoffeeshop.model.Image;
// import com.example.nooracoffeeshop.Repositories.ImageRepository;
// import com.example.nooracoffeeshop.model.Manufacturer;
// import com.example.nooracoffeeshop.Repositories.ManufacturerRepository;
// import com.example.nooracoffeeshop.model.Product;

// import com.example.nooracoffeeshop.Repositories.ProductRepository;
// import com.example.nooracoffeeshop.Repositories.SupplierRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import  java.util.List;





// @Controller
// public class AdminController {



    
//     @Autowired
//     private ManufacturerService manufacturerService; 



//     @Autowired
//     private SupplierService supplierService;

//     @Autowired
//     private ImageRepository imageRepository;



//     // **** Admin home *****


//     @GetMapping("/admin")
//     public String showAdminPage(){

//       return "admin_login";
     
//     }

//     //***Image addition ****

//     @GetMapping("/admin/imageAddition")
//     public String showImageAdditionForm(){
//       return "image";
//     }

//     @PostMapping("/admin/images/save")
//     public String saveImage(@RequestParam String name,@RequestParam String description,@RequestParam("image") MultipartFile image) throws IOException{
//        Image image1 = new Image();
//        image1.setName(name);
//        image1.setDescription(description);
//        image1.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
//        imageRepository.save(image1);
//        return "image";

//     }


    

//     // ***** supplier management ******


//     //add supplier & display supplier table
//     @GetMapping("/admin/supplierManagement")
//     public String displaySupplier(Model model){
//       model.addAttribute("suppliers", this.supplierService.listAll());
//       return "supplierManagement";
//     }

//     @PostMapping("/admin/supplier")
//     public String addSupplier(@RequestParam String supplierName,@RequestParam String contactMail ,@RequestParam String contactName){
//       this.supplierService.addSupplier(supplierName, contactName, contactMail);
//       return "supplierManagement";
//     }


//     // update supplier

//     @GetMapping("/updatesupplier/{id}")
//     public String showFormForUpdateSupplier(@PathVariable Long id , Model model){
  
//       model.addAttribute("supplier", this.supplierService.getSupplier(id));

//       return "updateSupplier";

//     }

//     @PostMapping("/updatesupplier/{id}")
//     public String updateSupplier(@RequestParam String supplierName,@RequestParam String contactMail ,@RequestParam String contactName,@PathVariable Long id){
          
//       this.supplierService.updateSupplier(id, supplierName, contactName, contactMail);

//       return "redirect:/supplierManagement";

//     }

//     // Delete_supplier


// @GetMapping("/deletesupplier/{id}")
// public String deleteSupplier(@PathVariable Long id){
//   this.supplierService.deleteSupplier(id);
//   return "redirect:/supplierManagement";
// }



//     // ***** Manufacturer management ******

//     @GetMapping("/admin/manufacturerManagement")
//     public String displayManufacturer(Model model){

//       model.addAttribute("manufacturers", this.manufacturerService.listAll());


//       return "manufacturerManagement";
//     }

//     @PostMapping("/admin/manufacturer")
//     public String addManufacturer(@RequestParam String manufacturerName,@RequestParam String manufacturerUrl){
//      this.manufacturerService.addManufacturer(manufacturerName, manufacturerUrl);
//     return "redirect:/anufacturerManagement";
//     }


//     // update supplier

//     @GetMapping("/updatemanufacturer/{id}")
//     public String showFormForUpdateManufacturer(@PathVariable Long id , Model model){
  
     
//       model.addAttribute("manufacturer", this.manufacturerService.getManufacturer(id));

//       return "updateManufacturer";

//     }

//     @PostMapping("/updateManufacturer/{id}")
//     public String updateManufacturer(@RequestParam String manufacturerName,@RequestParam String manufacturerUrl ,@PathVariable Long id){
//       this.manufacturerService.updateManufacturer(manufacturerName, manufacturerUrl,id);

//       return "redirect:/manufacturerManagement";

//     }
     
  
//     // Delete_supplier


// @GetMapping("/deletemanufacturer/{id}")
// public String deleteManufacturer(@PathVariable Long id){
//   this.manufacturerService.deleteManufacturer(id);
//   return "redirect:/manufacturerManagement";
// }
  
    
// }
