package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import com.example.onlineshopcomputerparts.entity.Laptop;
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
