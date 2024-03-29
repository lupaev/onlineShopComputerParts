package com.example.onlineshopcomputerparts.entity;


import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Сущность Товара
 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class Product {

  protected Integer serialNumber;
  protected String manufacturer;
  protected Double price;
  protected Integer quantity;
}
