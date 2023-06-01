package com.example.onlineshopcomputerparts.Entity;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Product {
  private int serialNumber;
  private String manufacturer;
  private int price;
  private int quantity;

}
