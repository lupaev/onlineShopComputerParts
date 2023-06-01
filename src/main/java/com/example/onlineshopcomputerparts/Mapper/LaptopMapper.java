package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import org.mapstruct.Mapper;

@Mapper
public interface LaptopMapper {

  Laptop toEntity(LaptopDTO laptopDTO);
  LaptopDTO toDTO(Laptop laptop);

}
