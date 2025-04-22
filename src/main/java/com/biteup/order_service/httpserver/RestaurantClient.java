package com.biteup.order_service.httpserver;

import com.biteup.order_service.model.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "restaurant", url = "http://localhost:8082")
public interface RestaurantClient {
  @GetMapping("/api/restaurant/{restaurantEmail}") // Replace @RequestMapping with @GetMapping
  Restaurant getRestaurantDataByEmail(
    @PathVariable("restaurantEmail") String restaurantEmail
  );
}
