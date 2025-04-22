package com.biteup.order_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

  @Id
  private String id;

  private String name;
  private String description;
  private String address;
  private String email;
  private String phoneNumber;
  private String placeId;
  private String city;
  private String state;
  private String zipCode;
  private Object logo;

  private Location location;
}
