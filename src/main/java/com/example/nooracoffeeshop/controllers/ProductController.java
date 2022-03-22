package com.example.nooracoffeeshop.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.nooracoffeeshop.Repositories.ProductRepository;

import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.services.DepartmentService;

import com.example.nooracoffeeshop.services.ManufacturerService;
import com.example.nooracoffeeshop.services.ProductService;
import com.example.nooracoffeeshop.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private ProductRepository productRepository;

    @GetMapping({ "/", "/index" })
    public String index(Model model) {

        return findPaginagted(0, model, null);
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String keyword) {

        return findPaginagted(0, model, keyword);
    }

    @GetMapping("/index/page/{pageno}")
    public String findPaginagted(@PathVariable int pageno, Model model, @Param("keyword") String keyword) {

        if (keyword != null) {

            Page<Product> productList = productService.getProductPaginate(pageno, 6);
            model.addAttribute("products", productService.listAll(keyword));
            model.addAttribute("currentPage", pageno);
            model.addAttribute("totalPages", productList.getTotalPages());
            model.addAttribute("totalItem", productList.getTotalElements());

        } else {
            Page<Product> productList = productService.getProductPaginate(pageno, 6);
            model.addAttribute("products", productList);
            model.addAttribute("currentPage", pageno);
            model.addAttribute("totalPages", productList.getTotalPages());
            model.addAttribute("totalItem", productList.getTotalElements());
            model.addAttribute("departments", departmentService.listAll());
        }
        return "index";
    }

    @GetMapping("/index/department/{id}")
    public String shopByCategory(Model model, @PathVariable Long id) {
        model.addAttribute("products",
                productService.getAllProductsByDepartmentId(id));
        model.addAttribute("departments", departmentService.listAll());
        return "index";
    }

    @GetMapping("/index/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProduct(id));

        return "viewProduct";
    }
    ///////////////////// ADMIN ////////////////////////////
    // ***** product management ******

    // add product
    @GetMapping("/admin/products/add")
    public String showProductAdditionForm(Model model) {
        model.addAttribute("manufacturers", manufacturerService.listAll());
        model.addAttribute("departments", departmentService.listAll());
        model.addAttribute("suppliers", supplierService.listAll());
        model.addAttribute("products", productService.listAll());
        model.addAttribute("product", new Product());
        // also displaying product table
        return "productAdd";
    }

    @GetMapping("/admin/product")
    public String showProducs(Model model) {

        model.addAttribute("manufacturers", manufacturerService.listAll());
        model.addAttribute("departments", departmentService.listAll());
        model.addAttribute("suppliers", supplierService.listAll());
        model.addAttribute("products", productService.listAll());

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
        model.addAttribute("product", new Product());
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
