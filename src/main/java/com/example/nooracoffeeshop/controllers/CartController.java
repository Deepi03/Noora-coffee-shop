package com.example.nooracoffeeshop.controllers;

import com.example.nooracoffeeshop.global.GlobalData;
import com.example.nooracoffeeshop.model.Product;
import com.example.nooracoffeeshop.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        GlobalData.cart.add(productService.getProduct(id));
        System.out.println("######" + productService.getProduct(id) + "#####");
        return "redirect:/index";
    }

    @GetMapping("/cart")
    public String cartItemRemove(Model model) {

        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total",
                GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        System.out.println("######" + GlobalData.cart + "#####");

        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index) {

        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";

    }

    @GetMapping("/paynow")
    public String paynow() {

        return "orderConfirmation";
    }

}