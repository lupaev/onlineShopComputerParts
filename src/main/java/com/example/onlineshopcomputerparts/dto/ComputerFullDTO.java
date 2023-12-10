package com.example.onlineshopcomputerparts.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ComputerFullDTO {
    private Long id;
    private Integer serialNumber;
    private String manufacturer;
    private Double price;
    private Integer quantity;
    private String form;
}
