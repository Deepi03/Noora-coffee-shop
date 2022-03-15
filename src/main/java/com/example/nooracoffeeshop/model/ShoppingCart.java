package com.example.nooracoffeeshop.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@Scope(value = "session" , proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingCart implements Serializable {

    private Map<Product,Long> products = new HashMap<>();

    public void addToCart(Product product){

        products.put(product,product.getId());

    }

    public double getSum(){
        double sum = 0;
        for(Product product : products.keySet()){
            sum += products.get(product);
        }
        return sum;
    }
    

    public void removeFromShopingCart(Product product){
        long count = products.get(product);
        if(count <= 1){
                        products.remove(product);
                    }else{
                        products.put(product, count -1);
                    }
    }
}
