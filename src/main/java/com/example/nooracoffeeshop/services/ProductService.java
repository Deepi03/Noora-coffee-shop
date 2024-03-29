package com.example.nooracoffeeshop.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.example.nooracoffeeshop.Repositories.DepartmentRepository;
// import com.example.nooracoffeeshop.Repositories.ImageRepository;
import com.example.nooracoffeeshop.Repositories.ManufacturerRepository;
import com.example.nooracoffeeshop.Repositories.ProductRepository;
import com.example.nooracoffeeshop.Repositories.SupplierRepository;
// import com.example.nooracoffeeshop.model.Image;
import com.example.nooracoffeeshop.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    // public Page<Product> topSellers(Pageable pageable) {
    // return productRepository.findAll(pageable);
    // }

    public List<Product> getAllProductsByDepartmentId(Long departmentId) {
        return productRepository.findAllByDepartment_Id(departmentId);
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.getById(id);
    }

    public void addProduct(Long manufacturerID, String productName, String productDescription, Long departmentID,
            Long supplierID, Double productPrice,
            String imageName) throws IOException {

        Product product = new Product();

        product.setName(productName);
        product.setPrice(productPrice);
        product.setDescription(productDescription);
        product.setDepartment(departmentRepository.getById(departmentID));
        product.setManufacturer(manufacturerRepository.getById(manufacturerID));
        product.setSupplier(supplierRepository.getById(supplierID));
        product.setImageName(imageName);

        productRepository.save(product);
    }

    public void updateProduct(Long id, Long manufacturerID, String productName, String productDescription,
            Long departmentID,
            Long supplierID, Double productPrice) {
        Product existingProduct = productRepository.getById(id);

        existingProduct.setDepartment(departmentRepository.getById(departmentID));
        existingProduct.setManufacturer(manufacturerRepository.getById(manufacturerID));
        existingProduct.setSupplier(supplierRepository.getById(supplierID));
        existingProduct.setName(productName);
        existingProduct.setPrice(productPrice);
        existingProduct.setDescription(productDescription);

        productRepository.save(existingProduct);

    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProductPaginate(int currentPage, int size) {
        PageRequest pageable = PageRequest.of(currentPage, size);
        return productRepository.findAll(pageable);
    }

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.findByNameContainingIgnoreCase(keyword);
        }
        return productRepository.findAll();
    }
}
