package com.biteup.order_service.service;

import com.biteup.order_service.dto.OrderRequestDTO;
import com.biteup.order_service.dto.OrderResponseDTO;
import com.biteup.order_service.httpserver.ProductClient;
import com.biteup.order_service.httpserver.RestaurantClient;
import com.biteup.order_service.model.CartResponse;
import com.biteup.order_service.model.Order;
import com.biteup.order_service.model.OrderMessage;
import com.biteup.order_service.model.Product;
import com.biteup.order_service.model.Restaurant;
import com.biteup.order_service.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final RestaurantClient restaurantClient;
  private final ProductClient productClient;

  public OrderResponseDTO placeorders(OrderRequestDTO req) {
    Order orders = new Order();
    orders.setEmail(req.getEmail());
    orders.setFoodId(req.getId());
    orders.setRestaurantEmail(req.getRestaurantEmail());
    orders.setQuantity(req.getQuantity());

    Order saved = orderRepository.save(orders);
    log.info("Order Created Successfully");

    if (saved.getId() == null) return new OrderResponseDTO(
      null,
      "System Error"
    );

    return new OrderResponseDTO("Cart Add Success", null);
  }

  public List<Order> getAllOrdersByUserEmail(String email) {
    return orderRepository.findByEmail(email);
  }

  public List<CartResponse> getCartFoodDetailsByFoodId(String email) {
    List<Order> orders = getAllOrdersByUserEmail(email);
    List<CartResponse> cartResponses = new ArrayList<>();

    for (Order order : orders) {
      try {
        Product product = productClient.getFoodDataById(order.getFoodId());
        Restaurant restaurant = restaurantClient.getRestaurantDataByEmail(
          order.getRestaurantEmail()
        );
        CartResponse cart = new CartResponse();

        cart.setFoodId(order.getFoodId());
        cart.setName(product.getName());
        cart.setDescription(product.getDescription());
        cart.setPrice(product.getPrice());
        cart.setImage(product.getImage());
        cart.setSignedUrl(product.getSignedUrl());

        cart.setQuantity(order.getQuantity());
        cart.setRestaurantEmail(order.getRestaurantEmail());

        cart.setResName(restaurant.getName());
        cart.setResdescription(restaurant.getDescription());
        cart.setAddress(restaurant.getAddress());
        cart.setEmail(restaurant.getEmail());
        cart.setPhoneNumber(restaurant.getPhoneNumber());
        cart.setPlaceId(restaurant.getPlaceId());
        cart.setCity(restaurant.getCity());
        cart.setState(restaurant.getState());
        cart.setZipCode(restaurant.getZipCode());
        cart.setLatitude(restaurant.getLocation().getLat());
        cart.setLongitude(restaurant.getLocation().getLng());

        cartResponses.add(cart);
      } catch (Exception e) {
        System.out.println("Product not found for id: " + order.getFoodId());
      }
    }

    return cartResponses;
  }

  @Service
  public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public void sendOrder(OrderMessage OrderMessage) {
      kafkaTemplate.send("assigned-orders", OrderMessage.getDeliveryPersonId(), OrderMessage);
    }
  }
}
