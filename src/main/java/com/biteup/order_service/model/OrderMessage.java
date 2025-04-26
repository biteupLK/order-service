package com.biteup.order_service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage {

  private String orderId;
  private String deliveryPersonId;
  private String location;
  private List<String> items;
}
