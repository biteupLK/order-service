package com.biteup.order_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.biteup.order_service.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{
    List<Order> findByEmail(String email);
}
