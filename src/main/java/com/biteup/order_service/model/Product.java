package com.biteup.order_service.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

  @Id
  private String id;

  private String name;
  private String description;
  private BigDecimal price;
  private String restaurantEmail;
  private String image;
  private String signedUrl;
}
