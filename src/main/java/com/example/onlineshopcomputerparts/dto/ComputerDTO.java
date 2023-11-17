package com.example.onlineshopcomputerparts.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO для Компьтера
 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ComputerDTO {

  @JsonIgnore
  Long id;

  private Integer serialNumber;

  private String manufacturer;

  private Double price;

  private Integer quantity;

  private String form;

}
