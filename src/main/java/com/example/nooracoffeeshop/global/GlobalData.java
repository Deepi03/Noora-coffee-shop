package com.example.nooracoffeeshop.global;

import java.util.ArrayList;
import java.util.List;

import com.example.nooracoffeeshop.model.Product;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
