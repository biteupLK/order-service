package com.biteup.order_service.controller;

import com.biteup.order_service.dto.OrderRequestDTO;
import com.biteup.order_service.dto.OrderResponseDTO;
import com.biteup.order_service.model.CartResponse;
import com.biteup.order_service.model.Order;
import com.biteup.order_service.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponseDTO> createProducts(
    @RequestBody OrderRequestDTO req
  ) {
    OrderResponseDTO res = orderService.placeorders(req);
    if (res.getError() != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
  }

  @GetMapping("/api/{email}")
  public List<Order> getAllProducts(@PathVariable String email) {
    return orderService.getAllOrdersByUserEmail(email);
  }

  @GetMapping("{id}")
  public List<CartResponse> getAllProductsByEmail(@PathVariable String id) {
    return orderService.getCartFoodDetailsByFoodId(id);
  }

  @PutMapping("/{id}")
public ResponseEntity<OrderResponseDTO> updateOrder(
    @PathVariable String id,
    @RequestBody OrderRequestDTO req
) {
    OrderResponseDTO res = orderService.updateOrder(id, req);
    if (res.getError() != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    } else {
        return ResponseEntity.ok(res);
    }
}

@DeleteMapping("/{id}")
public ResponseEntity<OrderResponseDTO> deleteOrder(@PathVariable String id) {
    OrderResponseDTO res = orderService.deleteOrder(id);
    if (res.getError() != null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    } else {
        return ResponseEntity.ok(res);
    }
}

}
