package com.example.nooracoffeeshop.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

// import com.example.nooracoffeeshop.Repositories.ImageRepository;
import com.example.nooracoffeeshop.Repositories.ProductRepository;

import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.services.DepartmentService;
// import com.example.nooracoffeeshop.services.ImageService;
import com.example.nooracoffeeshop.services.ManufacturerService;
import com.example.nooracoffeeshop.services.ProductService;
import com.example.nooracoffeeshop.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SupplierService supplierService;

    // @Autowired
    // private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String topSellers(Model model) {

        // Pageable pageable = PageRequest.of(0, 9,
        // Sort.by("productSold").descending());
        // model.addAttribute("products", this.productService.topSellers(pageable));
        return "index";

    }

    @GetMapping("/coffeemachines")
    public String getAllCoffeemachines(Model model) {
        // List<Long> coffeemachines = Arrays.asList(3L,4l,5L);
        // List<Product> products = productService.getAllCoffeeMachines(coffeemachines);
        // model.addAttribute("coffeemachines", products);
        return "coffeemachines";

    }

    @GetMapping("/consumerproducts")
    public String getAllConsumerProducts(Model model) {
        // List<Long> consumerproducts = Arrays.asList(6L, 7L);
        // List<Product> products =
        // productService.getAllConsumerProducts(consumerproducts);
        // model.addAttribute("consumerproducts", products);
        return "consumerproducts";
    }

    // @GetMapping("/consumerproducts/coffees")
    // public String getAllCoffees(Model model){
    // List<Long> coffees = Arrays.asList(8L, 9L);
    // List<Product> products = productService.getAllCoffees(coffees);
    // model.addAttribute("coffees", products);
    // return "consumerproducts";
    // }

    // ***** product management ******

    // add product
    @GetMapping("/admin/product")
    public String showProductAdditionForm(Model model) {

        model.addAttribute("manufacturers", manufacturerService.listAll());
        model.addAttribute("departments", departmentService.listAll());
        model.addAttribute("suppliers", supplierService.listAll());
        model.addAttribute("products", productService.listAll());
        model.addAttribute("product", new Product());

        // also displaying product table
        return "productManagement";

    }

    @PostMapping("/admin/product")
    public String addProduct(@RequestParam Long manufacturerID, @RequestParam String productName,
            @RequestParam String productDescription, @RequestParam Long departmentID, @RequestParam Long supplierID,
            @RequestParam Double productPrice,
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("imgName") String imgName, Model model) throws IOException {

        Product product = new Product();
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(manufacturerID, productName, productDescription, departmentID, supplierID,
                productPrice,
                imageUUID);

        return "redirect:/admin/product";
    }

    // update_product

    @GetMapping("/admin/updateproduct/{id}")
    public String showFormForUpdateProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.getById(id);
        model.addAttribute("manufacturers", this.manufacturerService.listAll());
        model.addAttribute("departments", this.departmentService.listAll());
        model.addAttribute("suppliers", this.supplierService.listAll());

        model.addAttribute("product", product);
        return "updateproduct";

    }

    @PostMapping("/admin/updateproduct/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam Long manufacturerID,
            @RequestParam String productName,
            @RequestParam String productDescription, @RequestParam Long departmentID,
            @RequestParam Long supplierID,
            @RequestParam Double productPrice) {

        this.productService.updateProduct(id, manufacturerID, productName, productDescription, departmentID, supplierID,
                productPrice);

        return "redirect:/admin/product";

    }

    // Delete_product

    @GetMapping("/admin/deleteproduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product";
    }

}
