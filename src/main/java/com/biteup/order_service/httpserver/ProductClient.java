package com.biteup.order_service.httpserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biteup.order_service.model.Product;

@FeignClient(value = "products", url = "http://localhost:8080")
public interface ProductClient {
    @GetMapping("/api/product/{id}")
    Product getFoodDataById(@PathVariable("id") String id);
}

