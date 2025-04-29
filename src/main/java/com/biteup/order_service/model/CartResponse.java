package com.biteup.order_service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponse {
    private String id;
    private String foodId;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private int quantity;
    private String restaurantEmail;
    private String signedUrl;
    private String ResName;
    private String Resdescription;
    private String address;
    private String email;
    private String phoneNumber;
    private String placeId;
    private String city;
    private String state;
    private String zipCode;
    private Object logo;
    private Double latitude;
    private Double longitude;
}
