package com.example.onlineshopcomputerparts.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LaptopDTO {

  @JsonIgnore
  Long id;
  @NotNull
  @NotBlank
  private int serialNumber;
  @NotNull
  @NotBlank
  private String manufacturer;
  @NotNull
  @NotBlank
  private double price;
  @NotNull
  @NotBlank
  private int quantity;
  @NotNull
  @NotBlank
  private int dioganal;

}
