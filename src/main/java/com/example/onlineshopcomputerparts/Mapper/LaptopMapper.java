package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Маппер для Ноутбука
 */

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LaptopMapper {

  Laptop toEntity(LaptopDTO laptopDTO);

  LaptopDTO toDTO(Laptop laptop);

  void updateLaptopFromDto(LaptopDTO laptopDTO, @MappingTarget Laptop laptop);

  Collection<LaptopDTO> toDTOList(Collection<Laptop> list);

}
